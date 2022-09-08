package io.nzbee.domain.bag;

import java.util.List;

import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.customer.ICustomer;
import io.nzbee.domain.promotion.value.CouponCode;

public interface IBag {

	Object getUserName();

	List<CouponCode> getCoupons();

	List<IRegularBagItem> getBagItems();

	Long getTotalQuantity();

	ICustomer getCustomer();

	void logItemError(String key, IBagItem bagItem);

}
