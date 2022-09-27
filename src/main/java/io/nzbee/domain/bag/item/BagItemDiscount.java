package io.nzbee.domain.bag.item;

import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.Money;

//remember the BagItemDiscount will not be persisted
//we only need what we need to compute the bagItem total
public class BagItemDiscount implements IBagItemDiscount {

	private IPromotionBagItem bagItem;
	
	public BagItemDiscount(IPromotionBagItem bi) {
		this.bagItem = bi;
	}

	@Override
	public Money getDiscountAmount() {
		return bagItem.getDiscountAmount();
	}

}
