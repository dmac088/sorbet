package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTO;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.entity.IBagItemService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class RegularBagItemDomainAdapter implements IBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagDomainDTOService bagService;
	
	@Autowired
	private IBagItemService bagItemService;
	
	@Autowired
	private IBagItemDomainDTOService bagItemDomainDTOService;
	
	@Autowired
	private IBagItemDomainDTOMapper<RegularBagItemDomainDTO, BagItemEntity, IRegularBagItem> regularBagItemDomainMapper;
	
	@Autowired
	private IBagItemDomainDTOMapper<ShippingBagItemDomainDTO, BagItemEntity, IShippingBagItem> shippingBagItemDomainMapper;

	@Override
	public IRegularBagItem getNewPhysicalItem(Locale locale, Bag bag, String itemUPC, Long quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewPhysicalItem with parameters {}, {}", itemUPC, quantity);
		
		//there is no product in the domain model just bagItem
		RegularBagItemDomainDTO biDto = bagItemDomainDTOService.getNewPhysicalItem(itemUPC, locale.getLanguageCode())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, itemUPC));
		
		//create, save and return domain object 
		IRegularBagItem bi = regularBagItemDomainMapper.DTOToDo(bag, biDto, quantity);

		return bi;
	}
	
	@Override
	public IShippingBagItem getNewShippingItem(Locale locale, Bag bag, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}", destCode, shipType);
		
		//there is no product in the domain model just bagItem
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getNewShippingItem(locale.getLanguageCode(),  destCode,  shipType, bag.getTotalWeight())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, destCode + " - " + shipType + " - " + bag.getTotalWeight()));
		
		//create, save and return domain object 
		IShippingBagItem bi = shippingBagItemDomainMapper.DTOToDo(bag, biDto);

		return bi;
	}
	
	@Override
	public void delete(IRegularBagItem domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete with parameters {}", domainObject.getBagItem().getProductUPC());
		Optional<PersonEntity> op = personService.findByUsernameAndRole(domainObject.getBagItem().getBag().getCustomer().getUserName(), Constants.partyRoleCustomer);
		BagEntity b = op.get().getPersonParty().getBag();
		Optional<BagItemEntity> obi = b.getBagItems().stream().filter(bi -> (new ProductUPC(bi.getProduct().getProductUPC())).sameAs((domainObject.getBagItem().getProductUPC()))).findAny();
		io.nzbee.entity.bag.item.entity.BagItemEntity bi = obi.get();
		b.removeItem(bi);
		bagService.save(b); 
	}

	@Override
	public void save(IRegularBagItem domainObject) {
		bagItemService.save(regularBagItemDomainMapper.doToEntity(domainObject));
	}
	
	@Override
	public void save(IShippingBagItem domainObject) {
		bagItemService.save(shippingBagItemDomainMapper.doToEntity(domainObject));
	}

}
