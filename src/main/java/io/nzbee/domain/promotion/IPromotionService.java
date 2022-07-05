package io.nzbee.domain.promotion;

public interface IPromotionService  {

	Promotion findByCode(String locale, String code);

	Promotion findByCouponCode(String locale, String coupon);

}
