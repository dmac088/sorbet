package io.nzbee.domain.ports;


import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.promotion.Promotion;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	IBag applyAll(IBag bag);

	Promotion findByCode(String coupon); 
	
}
