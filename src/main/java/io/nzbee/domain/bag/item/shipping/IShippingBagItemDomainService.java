package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.valueObjects.Locale;

public interface IShippingBagItemDomainService {

	IShippingBagItem getShippingItem(Locale locale, Bag b, String code);

	IShippingBagItem getNewShippingItem(Locale locale, Bag bag, String destCode, String shipType);


}
