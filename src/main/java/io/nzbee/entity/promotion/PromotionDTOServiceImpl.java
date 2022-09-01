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
	public List<PromotionDomainDTO> findAll(List<String> coupons, List<String> items) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll()");
		return promotionDao.findAll(coupons, items);
	}
	
	
}
