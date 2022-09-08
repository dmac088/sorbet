package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.Bag;

public interface IShippingBagItemDomainService {

	IShippingBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	IShippingBagItem getShippingItem(String currency, Bag b, String code);


}
