package io.nzbee.domain.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IPromotionBag;
import io.nzbee.domain.ports.IPromotionPortService;

public class PromotionServiceImpl implements IPromotionService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionPortService promotionService;
	
	@Override
	public IPromotionBag applyAll(Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".applyAll with parameter {}", bag.getCustomer().getUserName());
		return promotionService.applyAll(bag);
	}

	@Override
	public Promotion findByCouponCode(String coupon) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCouponCode with parameter {}", coupon);
		return promotionService.findByCode(coupon);
	}

	
}
