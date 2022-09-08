package io.nzbee.domain.promotion;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IPromotionBag;

public interface IPromotionService  {

	IPromotionBag applyAll(Bag bag);

	Promotion findByCouponCode(String coupon);
	
}
