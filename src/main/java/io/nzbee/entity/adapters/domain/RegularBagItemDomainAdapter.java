package io.nzbee.entity.adapters.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTO;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class RegularBagItemDomainAdapter implements IBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagItemDomainDTOService bagItemDomainDTOService;
	
	@Autowired
	private IBagItemDomainDTOMapper<RegularBagItemDomainDTO, BagItemEntity, IRegularBagItem> regularBagItemDomainMapper;
	
	@Autowired
	private IBagItemDomainDTOMapper<ShippingBagItemDomainDTO, BagItemEntity, IShippingBagItem> shippingBagItemDomainMapper;

	@Override
	public IRegularBagItem getNewPhysicalItem(Locale locale, IBag bag, String itemUPC, Long quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewPhysicalItem with parameters {}, {}", itemUPC, quantity);
		
		//there is no product in the domain model just bagItem
		RegularBagItemDomainDTO biDto = bagItemDomainDTOService.getNewPhysicalItem(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), itemUPC)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, itemUPC));
		
		//create, save and return domain object 
		IRegularBagItem bi = regularBagItemDomainMapper.toDo(bag, biDto, quantity);

		return bi;
	}
	
	@Override
	public IShippingBagItem getNewShippingItem(Locale locale, IBag bag, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}", destCode, shipType);
		
		//there is no product in the domain model just bagItem
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getNewShippingItem(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(),  destCode,  shipType, bag.getTotalWeight().amount())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, destCode + " - " + shipType + " - " + bag.getTotalWeight().amount()));
		
		//create, save and return domain object 
		IShippingBagItem bi = shippingBagItemDomainMapper.toDo(bag, biDto);

		return bi;
	}
	

}
