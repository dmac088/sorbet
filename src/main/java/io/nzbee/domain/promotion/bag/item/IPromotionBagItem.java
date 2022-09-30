package io.nzbee.domain.promotion.bag.item;

import java.util.List;

import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.Percentage;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public interface IPromotionBagItem {

	List<CategoryCode> getCategoryCodes();

	BrandCode getBrandCode();

	ProductUPC getItemUPC();

	Quantity getQuantity();

	Money getPrice();;

	Money getTotalAmount();

	IPromotionBag getBag();

	BagItemStatus getBagItemStatus();

	Percentage getDiscountPercentage();

	void addDiscount(Percentage discount);


}
