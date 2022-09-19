package io.nzbee.domain.bag;

import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.bag.item.BagItemViewIn;
import io.nzbee.view.product.shipping.ShippingItemDTOIn;

public interface IBagDomainService  {

	Bag findByCode(Locale locale, String userName);
	
	void checkAllBagRules(Bag object);

	void save(Bag object);

	void update(Bag object);

	void addPhysicalItem(Locale locale, BagItemViewIn dto, String username);

	void addShippingItem(Locale locale, ShippingItemDTOIn dto, String username);

	void addItemToBag(Locale locale, CouponCode coupon, String username);


	

}
