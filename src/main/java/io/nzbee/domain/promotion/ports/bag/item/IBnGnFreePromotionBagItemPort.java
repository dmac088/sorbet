package io.nzbee.domain.promotion.ports.bag.item;

import java.math.BigDecimal;

public interface IBnGnFreePromotionBagItemPort {

	BigDecimal getTotalAmount();
	
	Long getTotalQuantity();
	
	Boolean qualified(String upc);
	
}
