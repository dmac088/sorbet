package io.nzbee.domain.promotion.bag.item;

import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.PromotionCode;

public class PromotionDiscountItem implements IPromotionDiscountItem {

	private final PromotionCode promotionCode;
	
	private final Money discountAmount;
	
	private final IPromotionBagItem promotionBagItem;

	public PromotionDiscountItem(IPromotionBagItem bagItem,
								 PromotionCode promotionCode, 
								 Money discountAmount) {
		super();
		this.promotionBagItem = bagItem;
		this.promotionCode = promotionCode;
		this.discountAmount = discountAmount;
	}

	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	@Override
	public Money getDiscountAmount() {
		return discountAmount;
	}

	@Override
	public IPromotionBagItem getPromotionBagItem() {
		return promotionBagItem;
	}
	
}
