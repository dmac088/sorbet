package io.nzbee.domain.promotion.bag;

import java.util.List;

import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;

public interface IPromotionBag {

	UserName getUserName();

	Quantity getQuantity();
	
	int getItemCount();

	Boolean isErrors();

	void setErrors(Boolean errors);

	String getError();

	void setError(String error);

	Money getMoney();

	List<IPromotionBagItem> getBagItems();

	Money getTotalAmount();

	List<CouponCode> getCoupons();

}
