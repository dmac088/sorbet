package io.nzbee.domain.ports;


import io.nzbee.domain.bag.IPromotionBag;
import io.nzbee.domain.promotion.Promotion;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	Promotion findByCode(String coupon);

	IPromotionBag applyAll(IPromotionBag bag); 
	
}
