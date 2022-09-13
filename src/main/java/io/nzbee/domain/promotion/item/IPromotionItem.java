package io.nzbee.domain.promotion.item;

import java.util.List;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;

public interface IPromotionItem {

	List<CategoryCode> getCategoryCodes();

	BrandCode getBrandCode();

	ProductUPC getItemUPC();

	Money getDiscountAmount();

	Long getQuantity();

	Money getPrice();

	List<CouponCode> getCoupons();

	void addDiscount(Money discount);

	Money getTotalAmount();


}
