package io.nzbee.domain.bag;

import io.nzbee.view.bag.item.BagItemViewIn;

public interface IBagDomainService  {

	Bag findByCode(String locale, String currency, String userName);
	
	void checkAllBagRules(Bag object);

	void save(Bag object);

	void update(Bag object);

	Bag addPhysicalItem(String locale, String currency, BagItemViewIn dto, String username);

	Bag addShippingItem(String locale, String currency, BagItemViewIn dto, String username);

	

}
