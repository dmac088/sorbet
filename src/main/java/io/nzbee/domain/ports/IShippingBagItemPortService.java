package io.nzbee.domain.ports;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.valueObjects.Locale;

public interface IShippingBagItemPortService {

	IShippingBagItem getShippingItem(IBag b, String code, Locale locale);


}
