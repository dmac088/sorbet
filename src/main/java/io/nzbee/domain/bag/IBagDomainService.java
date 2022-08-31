package io.nzbee.domain.bag;

import io.nzbee.view.bag.item.BagItemViewIn;
import io.nzbee.view.product.shipping.ShippingItemDTOIn;

public interface IBagDomainService  {

	Bag findByCode(String locale, String currency, String userName);
	
	void checkAllBagRules(Bag object);

	void save(Bag object);

	void update(Bag object);

	void addPhysicalItem(String locale, String currency, BagItemViewIn dto, String username);

	void addShippingItem(String locale, String currency, ShippingItemDTOIn dto, String username);

	void addItemToBag(String locale, String currency, String coupon, String name);

	

}
