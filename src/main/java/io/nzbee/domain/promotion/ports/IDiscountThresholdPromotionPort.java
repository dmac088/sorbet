package io.nzbee.domain.promotion.ports;

import java.math.BigDecimal;

public interface IDiscountThresholdPromotionPort {

	BigDecimal getTotalAmount();
	
}
