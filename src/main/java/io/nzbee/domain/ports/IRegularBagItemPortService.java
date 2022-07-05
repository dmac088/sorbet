package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;

public interface IRegularBagItemPortService extends IDomainPortService<RegularBagItem> {
	
	void delete(RegularBagItem domainObject);
	
	void save(RegularBagItem domainObject);

	RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, int quantity);

	RegularBagItem getNewShippingItem(String locale, String currency, Bag bag, String destCode, String shipType);

}
