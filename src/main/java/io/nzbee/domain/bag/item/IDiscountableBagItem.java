package io.nzbee.domain.bag.item;

import java.math.BigDecimal;

public interface IDiscountableBagItem {

	String getUPC();
	
	BigDecimal getTotalAmount();
	
	BigDecimal getBagTotalAmount();
	
	void addDiscount(BagItemDiscount discountItem);
}
 