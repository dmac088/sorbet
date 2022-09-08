package io.nzbee.domain.bag.item.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IShippingBagItemPortService;

public class ShippingBagItemDomainServiceImpl implements IShippingBagItemDomainService{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingBagItemPortService shippingBagItemService;
	
	@Override
	public IShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem with parameters {}, {}, {}", destCode, shipType, bag.getTotalWeight());
		return shippingBagItemService.getNewShippingItem(locale, currency, bag, destCode,  shipType);
	}
	
	@Override
	public IShippingBagItem getShippingItem(String currency, Bag b, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem with parameters {}");
		return shippingBagItemService.getShippingItem(b, code, currency);
	}
	
}
