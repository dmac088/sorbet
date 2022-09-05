package io.nzbee.entity.adapters.domain;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.ports.IShippingBagItemPortService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.item.domain.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IShippingBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.entity.IBagItemService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class ShippingBagItemDomainAdapter implements IShippingBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagItemService bagItemService;
	
	@Autowired
	private IBagItemDomainDTOService bagItemDomainDTOService;
	
	@Autowired
	private IRegularBagItemDomainDTOMapper regularBagItemDomainMapper;
	
	@Autowired
	private IShippingBagItemDomainDTOMapper shippingBagItemDomainMapper;

	
	@Override
	@Transactional
	public ShippingBagItem getShippingItem(Bag b, String code, String currency) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem with parameters {}, {}, {}", currency, Constants.markdownPriceCode, code);
		
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getShippingItem(currency, Constants.markdownPriceCode, code)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, Constants.localeENGB, code));
		
		ShippingBagItem sbi = shippingBagItemDomainMapper.DTOToDo(b, biDto);
		
		return sbi;
	}
	
	@Override
	@Transactional
	public ShippingBagItem getNewShippingItem(String locale, String currency, Bag b, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}, {}, {}", locale, currency, destCode, shipType);
		
		//there is no product in the domain model just bagItem
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getNewShippingItem(currency,  destCode,  shipType, b.getTotalWeight())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, destCode + " - " + shipType + " - " + b.getTotalWeight()));
		
		//create, save and return domain object 
		ShippingBagItem sbi = shippingBagItemDomainMapper.DTOToDo(b, biDto);
		
		this.save(sbi);
		
		return sbi;
	}
	
	@Override
	@Transactional
	public void delete(ShippingBagItem domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete()");
		Optional<PersonEntity> op = personService.findByUsernameAndRole(domainObject.getBagItem().getBag().getCustomer().getUserName(), Constants.partyRoleCustomer);
		BagEntity b = op.get().getPersonParty().getBag();
		Optional<BagItemEntity> obi = b.getBagItems().stream().filter(bi -> bi.getBagItemType().getBagItemTypeCode().equals(Constants.shippingBagItemType)).findAny();		
		if(obi.isPresent()) {
			bagItemDomainDTOService.delete(obi.get());
		}
	}
	
	@Override
	@Transactional
	public void save(RegularBagItem domainObject) {
		bagItemService.save(regularBagItemDomainMapper.doToEntity(domainObject));
	}
	
	@Override
	@Transactional
	public void save(ShippingBagItem domainObject) {
		bagItemService.save(shippingBagItemDomainMapper.doToEntity(domainObject));
	}


}
