package io.nzbee.domain.bag;

import java.util.List;
import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.customer.ICustomer;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.domain.valueObjects.Weight;

public interface IBag {

	UserName getUserName();

	List<CouponCode> getCoupons();

	List<IRegularBagItem> getBagItems();

	Long getTotalQuantity();

	ICustomer getCustomer();

	void logItemError(String key, IBagItem bagItem);

	void setErrors(Boolean errors);

	void setError(String error);

	String getError();

	Boolean isErrors();

	int getTotalItems();

	Money getSubTotalAmount();

	Boolean hasIssues();

	void removeItem(IRegularBagItem iRegularBagItem);

	Weight getTotalWeight();

	IShippingBagItem getShippingItem();

	Boolean hasShippingItem();

	Locale getLocale();

	void addCoupon(CouponCode coupon);

	void addShippingItem(IShippingBagItem sbi);

	Boolean bagItemExists(ProductUPC productUPC);

	IRegularBagItem getBagItem(ProductUPC productUPC);

	void addItem(IRegularBagItem bagItem, Quantity quantity);

	Money getMoney();

}
