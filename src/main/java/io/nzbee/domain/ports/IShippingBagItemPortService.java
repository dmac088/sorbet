package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.valueObjects.Locale;

public interface IShippingBagItemPortService {

	IShippingBagItem getShippingItem(Bag b, String code, Locale locale);

}
