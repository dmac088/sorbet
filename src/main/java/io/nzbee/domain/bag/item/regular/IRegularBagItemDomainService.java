package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.Bag;

public interface IRegularBagItemDomainService {

	void save(RegularBagItem object);

	void delete(RegularBagItem object);

	RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, int quantity);

	RegularBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

	void checkAllBagItemRules(RegularBagItem object);
}
