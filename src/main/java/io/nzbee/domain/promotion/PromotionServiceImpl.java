package io.nzbee.domain.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.nzbee.domain.bag.Bag;

public class PromotionServiceImpl implements IPromotionService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public Promotion findAll(Bag b) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll() with parameters {}", b.getCustomer().getUserName());
		return null;
	}
	
}
