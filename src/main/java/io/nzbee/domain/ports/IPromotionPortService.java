package io.nzbee.domain.ports;

import io.nzbee.domain.promotion.Promotion;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	Promotion findByCode(String locale, String code);

	void save(Promotion domainObject);

	Promotion findPromotionByCouponCode(String locale, String couponCode);

	Promotion findPromotionByCode(String locale, String code);
	
}
