package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.Bag;

public interface IRegularBagItemDomainService {

	void save(RegularBagItem object);

	void delete(RegularBagItem object);

	void checkAllBagItemRules(RegularBagItem object);

	RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, Long quantity);
}
