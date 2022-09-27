package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.Locale;

public interface IShippingBagItemDomainService {

	IShippingBagItem getShippingItem(Locale locale, IBag b, String code);

}
