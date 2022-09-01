package io.nzbee.entity.promotion;

import java.util.Optional;

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
	public Optional<PromotionDomainDTO> findProductPromotion(String upc, String triggerCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll parameters : {}, {}", upc, triggerCode);
		return promotionDao.findProductPromotion(upc, triggerCode);
	}
	
	@Override
	public Optional<PromotionDomainDTO> findBagPromotion(String triggerCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findBagPromotion parameters : {}", triggerCode);
		return promotionDao.findBagPromotion(triggerCode);
	}
	
}
