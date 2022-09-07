package io.nzbee.domain.bag.item;

import java.util.List;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;

public interface IDiscountableBagItem {

	ProductUPC getUPC();
	
	BrandCode getBrandCode();
	
	List<CategoryCode> getCategoryCodes();
	
	Money getTotalAmount();
	
	Money getBagTotalAmount();
	
	void addDiscount(BagItemDiscount discountItem);

}
 