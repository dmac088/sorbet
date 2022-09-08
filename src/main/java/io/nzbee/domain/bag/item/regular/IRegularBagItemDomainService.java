package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.Bag;

public interface IRegularBagItemDomainService {

	void save(IRegularBagItem object);

	void delete(IRegularBagItem iRegularBagItem);

	void checkAllBagItemRules(IRegularBagItem bagItem);

	IRegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, Long quantity);
}
