package io.nzbee.entity.promotion;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PromotionDTOServiceImpl implements IPromotionDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionDao promotionDao;

	public void validateCouponCode(String locale, String currency, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".validateCouponCode with parameters {}, {}, {}", locale, currency, code);
		
	}

	@Override
	public List<PromotionDomainDTO> findShippingPromotions() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findShippingPromotion()");
		return promotionDao.findShippingPromotions();
	}
	@Override
	public List<PromotionDomainDTO> findBagPromotions() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findBagPromotions()");
		return promotionDao.findBagPromotions();
	}
	@Override
	public List<PromotionDomainDTO> findItemPromotion(String itemUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findItemPromotion()");
		return promotionDao.findItemPromotion(itemUPC);
	}
	
	
}
