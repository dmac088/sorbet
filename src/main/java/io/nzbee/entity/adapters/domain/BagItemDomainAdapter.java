package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.ports.IRegularBagItemPortService;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.item.domain.regular.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.regular.IRegularBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.entity.IBagItemService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class BagItemDomainAdapter implements IRegularBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagDomainDTOService bagService;
	
	@Autowired
	private IBagItemService bagItemService;
	
	@Autowired
	private IRegularBagItemDomainDTOService bagItemDomainDTOService;
	
	@Autowired
	private IRegularBagItemDomainDTOMapper bagItemDomainMapper;

	@Override
	public RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, int quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewPhysicalItem with parameters {}, {}", itemUPC, quantity);
		
		//there is no product in the domain model just bagItem
		RegularBagItemDomainDTO biDto = bagItemDomainDTOService.getNewPhysicalItem(itemUPC, currency)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, itemUPC));
		
		//create, save and return domain object 
		RegularBagItem bi = bagItemDomainMapper.DTOToDo(bag, biDto, quantity);
		
		this.save(bi);
		return bi;
	}
	
	@Override
	public RegularBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}", destCode, shipType);
		
		//there is no product in the domain model just bagItem
		RegularBagItemDomainDTO biDto = bagItemDomainDTOService.getNewShippingItem(currency,  destCode,  shipType, bag.getTotalWeight())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, destCode + " - " + shipType + " - " + bag.getTotalWeight()));
		
		//create, save and return domain object 
		RegularBagItem bi = bagItemDomainMapper.DTOToDo(bag, biDto, 1);
		this.save(bi);
		return bi;
	}
	
	@Override
	public void delete(RegularBagItem domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete with parameters {}", domainObject.getBagItem().getProductUPC());
		Optional<PersonEntity> op = personService.findByUsernameAndRole(domainObject.getBagItem().getBag().getCustomer().getUserName(), Constants.partyRoleCustomer);
		BagEntity b = op.get().getPersonParty().getBag();
		Optional<BagItemEntity> obi = b.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals(domainObject.getBagItem().getProductUPC())).findAny();
		io.nzbee.entity.bag.item.entity.BagItemEntity bi = obi.get();
		b.removeItem(bi);
		bagService.save(b); 
	}

	@Override
	public void save(RegularBagItem domainObject) {
		bagItemService.save(bagItemDomainMapper.doToEntity(domainObject));
	}

}
