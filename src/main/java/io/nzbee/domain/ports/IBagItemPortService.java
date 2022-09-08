package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;

public interface IBagItemPortService extends IDomainPortService<RegularBagItem> {
	
	void delete(IRegularBagItem domainObject);
	
	void save(IRegularBagItem object);

	IRegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, Long quantity);

	IShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	void save(IShippingBagItem domainObject);

}
