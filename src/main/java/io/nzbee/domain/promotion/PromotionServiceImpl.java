package io.nzbee.domain.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IPromotionPortService;

public class PromotionServiceImpl implements IPromotionService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionPortService promotionService;
	
	@Override
	public Promotion findByCode(String locale, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}", locale, code);
		return promotionService.findByCode(locale, code);
	}

	@Override
	public Promotion findByCouponCode(String locale, String coupon) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCouponCode with parameters {}, {}", locale, coupon);
		return promotionService.findPromotionByCouponCode(locale, coupon);
	}
	
}
