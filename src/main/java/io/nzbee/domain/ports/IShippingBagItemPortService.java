package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;

public interface IShippingBagItemPortService {

	ShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	ShippingBagItem getShippingItem(Bag b, String code, String currency);

	void save(RegularBagItem domainObject);

	void save(ShippingBagItem domainObject);

	void delete(ShippingBagItem domainObject);



}
