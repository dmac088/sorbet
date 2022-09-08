package io.nzbee.domain.promotion;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBag;

public interface IPromotionService  {

	IBag applyAll(Bag bag);

	Promotion findByCouponCode(String coupon);
	
}
