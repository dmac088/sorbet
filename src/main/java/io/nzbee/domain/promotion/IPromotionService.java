package io.nzbee.domain.promotion;

import io.nzbee.domain.bag.Bag;

public interface IPromotionService  {

	Bag applyAll(Bag bag);

	void validateCouponCode(String locale, String currency, String code);
	
}
