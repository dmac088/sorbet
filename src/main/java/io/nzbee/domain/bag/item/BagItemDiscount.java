package io.nzbee.domain.bag.item;

import io.nzbee.domain.promotion.item.IPromotionItem;
import io.nzbee.domain.valueObjects.Money;

//remember the BagItemDiscount will not be persisted
//we only need what we need to compute the bagItem total
public class BagItemDiscount implements IBagItemDiscount {

	private IPromotionItem bagItem;
	
	public BagItemDiscount(IPromotionItem bi) {
		this.bagItem = bi;
	}

	@Override
	public Money getDiscountAmount() {
		return bagItem.getDiscountAmount();
	}

}
