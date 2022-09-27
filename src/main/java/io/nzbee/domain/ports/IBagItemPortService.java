package io.nzbee.domain.ports;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.valueObjects.Locale;

public interface IBagItemPortService extends IDomainPortService<RegularBagItem> {
	
	IRegularBagItem getNewPhysicalItem(Locale locale, IBag bag, String itemUPC, Long quantity);

	IShippingBagItem getNewShippingItem(Locale locale, IBag bag, String destCode, String shipType);

}
