package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.valueObjects.Locale;

public interface IShippingBagItemPortService {
	
	IShippingBagItem getNewShippingItem(Locale locale, Bag b, String destCode, String shipType);

	IShippingBagItem getShippingItem(Bag b, String code, Locale locale);

	void save(IShippingBagItem domainObject);

	void delete(IShippingBagItem domainObject);


}
