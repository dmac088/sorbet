package io.nzbee.domain.bag.item;

import io.nzbee.domain.promotion.value.Money;

public interface IBagItemDiscount {

	Money getDiscountAmount();

	IDiscountableBagItem getBagItem();

}
