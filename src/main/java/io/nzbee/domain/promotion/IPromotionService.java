package io.nzbee.domain.promotion;

import io.nzbee.domain.bag.Bag;

public interface IPromotionService  {

	Bag applyAll(Bag bag);

	Promotion findByCouponCode(String coupon);
	
}
