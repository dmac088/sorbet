package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.Bag;

public interface IShippingBagItemDomainService {

	ShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	ShippingBagItem getShippingItem(String currency, Bag b, String code);


}
