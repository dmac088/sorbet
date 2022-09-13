package io.nzbee.domain.ports;


import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.item.IPromotionItem;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	Promotion findByCode(String coupon);

	void applyAll(IPromotionItem bag); 
	
}
