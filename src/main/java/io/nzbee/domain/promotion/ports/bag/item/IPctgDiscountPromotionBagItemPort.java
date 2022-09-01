package io.nzbee.domain.promotion.ports.bag.item;

import java.math.BigDecimal;

public interface IPctgDiscountPromotionBagItemPort {

	BigDecimal getTotalAmount();
	
	Boolean qualified(String upc);
	
}
