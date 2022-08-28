package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;

public interface IBagItemPortService extends IDomainPortService<RegularBagItem> {
	
	void delete(RegularBagItem domainObject);
	
	void save(RegularBagItem domainObject);

	RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, Long quantity);

	ShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	void save(ShippingBagItem domainObject);

}
