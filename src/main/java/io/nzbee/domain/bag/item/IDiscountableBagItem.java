package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.List;

public interface IDiscountableBagItem {

	String getUPC();
	
	String getBrandCode();
	
	List<String> getCategoryCodes();
	
	BigDecimal getTotalAmount();
	
	BigDecimal getBagTotalAmount();
	
	void addDiscount(BagItemDiscount discountItem);

}
 