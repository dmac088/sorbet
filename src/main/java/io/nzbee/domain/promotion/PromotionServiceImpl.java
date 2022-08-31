package io.nzbee.domain.promotion;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;

public class PromotionServiceImpl implements IPromotionService {

	@Autowired
	private IPromotionPortService promotionService;
	
	@Override
	public Bag applyAll(Bag bag) {
		return promotionService.applyAll(bag);
	}

	@Override
	public void validateCouponCode(String locale, String currency, String code) {
		System.out.println("validating coupon code " + code);
	}

	
}
