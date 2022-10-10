package io.nzbee.domain.bag;

import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.view.bag.item.BagItemViewIn;
import io.nzbee.view.shipping.ShippingItemDTOIn;

public interface IBagDomainService  {

	IBag findByCode(Locale locale, UserName userName);
	
	void save(IBag object);

	void update(IBag object);

	IBag addPhysicalItem(Locale locale, BagItemViewIn dto, UserName username);

	IBag addCouponToBag(Locale locale, CouponCode coupon, UserName username);

	IBag addShippingItem(Locale locale, ShippingItemDTOIn dto, UserName username);


}
