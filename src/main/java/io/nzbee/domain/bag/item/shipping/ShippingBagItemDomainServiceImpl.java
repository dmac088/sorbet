package io.nzbee.domain.bag.item.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IShippingBagItemPortService;
import io.nzbee.domain.valueObjects.Locale;

public class ShippingBagItemDomainServiceImpl implements IShippingBagItemDomainService{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingBagItemPortService shippingBagItemService;
	
	@Override
	public IShippingBagItem getNewShippingItem(Locale locale, Bag bag, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}, {}", destCode, shipType, bag.getTotalWeight());
		return shippingBagItemService.getNewShippingItem(locale, bag, destCode,  shipType);
	}
	
	@Override
	public IShippingBagItem getShippingItem(Locale locale, Bag b, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem with parameters {}");
		return shippingBagItemService.getShippingItem(b, code, locale);
	}
	
}
