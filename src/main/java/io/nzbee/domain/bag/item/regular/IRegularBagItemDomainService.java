package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.valueObjects.Locale;

public interface IRegularBagItemDomainService {

	IRegularBagItem getNewPhysicalItem(Locale locale, Bag b, String itemUPC, Long itemQty);

	void checkAllBagItemRules(IRegularBagItem object);

}
