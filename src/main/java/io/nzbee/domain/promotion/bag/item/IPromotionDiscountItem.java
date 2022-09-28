package io.nzbee.domain.promotion.bag.item;

import io.nzbee.domain.valueObjects.Money;

public interface IPromotionDiscountItem {

	Money getDiscountAmount();

	IPromotionBagItem getPromotionBagItem();

}
