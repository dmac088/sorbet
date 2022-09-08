package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;

public interface IShippingBagItemPortService {

	IShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	IShippingBagItem getShippingItem(Bag b, String code, String currency);

	void save(IShippingBagItem domainObject);

	void delete(IShippingBagItem domainObject);



}
