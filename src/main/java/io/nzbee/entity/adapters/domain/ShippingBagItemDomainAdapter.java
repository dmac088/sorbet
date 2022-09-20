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
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.ports.IShippingBagItemPortService;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.entity.BagEntity;
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
	private IShippingBagItemDomainDTOMapper shippingBagItemDomainMapper;

	
	@Override
	@Transactional
	public IShippingBagItem getShippingItem(Bag b, String code, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem with parameters {}, {}, {}", locale.getLocale(), Constants.markdownPriceCode, code);
		
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getShippingItem(locale.getLanguageCode(), Constants.markdownPriceCode, code)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, code));
		
		IShippingBagItem sbi = shippingBagItemDomainMapper.DTOToDo(b, biDto);
		
		return sbi;
	}
	
	@Override
	@Transactional
	public IShippingBagItem getNewShippingItem(Locale locale, Bag b, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), destCode, shipType);
		
		//there is no product in the domain model just bagItem
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getNewShippingItem(locale.getLanguageCode(), destCode, shipType, b.getTotalWeight())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, destCode + " - " + shipType + " - " + b.getTotalWeight()));
		
		//create, save and return domain object 
		IShippingBagItem sbi = shippingBagItemDomainMapper.DTOToDo(b, biDto);
		
		this.save(sbi);
		
		return sbi;
	}
	
	@Override
	@Transactional
	public void delete(IShippingBagItem domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete()");
		Optional<PersonEntity> op = personService.findByUsernameAndRole(domainObject.getUserName(), Constants.partyRoleCustomer);
		BagEntity b = op.get().getPersonParty().getBag();
		Optional<BagItemEntity> obi = b.getBagItems().stream().filter(bi -> bi.getBagItemType().getBagItemTypeCode().equals(Constants.shippingBagItemType)).findAny();		
		if(obi.isPresent()) {
			bagItemDomainDTOService.delete(obi.get());
		}
	}
	
	@Override
	@Transactional
	public void save(IShippingBagItem domainObject) {
		bagItemService.save(shippingBagItemDomainMapper.doToEntity(domainObject));
	}


}
