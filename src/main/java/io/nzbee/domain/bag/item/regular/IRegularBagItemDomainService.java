package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.valueObjects.Locale;

public interface IRegularBagItemDomainService {

	void save(IRegularBagItem object);

	void delete(IRegularBagItem iRegularBagItem);

	void checkAllBagItemRules(IRegularBagItem bagItem);

	IRegularBagItem getNewPhysicalItem(Locale locale, Bag b, String itemUPC, Long itemQty);
}
