package io.nzbee.domain.promotion.ports;

import java.math.BigDecimal;

public interface IBnGnFreePromotionPort {

	BigDecimal getTotalAmount();
	
	Long getTotalQuantity();
	
}
