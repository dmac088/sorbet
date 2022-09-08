package io.nzbee.domain.bag;

import java.util.List;

import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.promotion.value.CouponCode;

public interface IPromotionBag {

	List<CouponCode> getCoupons();

	List<IRegularBagItem> getBagItems();

}
