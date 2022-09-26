package io.nzbee.domain.promotion.bag;

import java.util.List;

import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;

public interface IPromotionItem {

	List<CategoryCode> getCategoryCodes();

	BrandCode getBrandCode();

	ProductUPC getItemUPC();

	Money getDiscountAmount();

	Long getQuantity();

	Money getPrice();;

	void addDiscount(Money discount);

	Money getTotalAmount();


}
