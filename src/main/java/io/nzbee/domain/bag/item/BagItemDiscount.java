package io.nzbee.domain.bag.item;

import io.nzbee.domain.promotion.value.Money;

//remember the BagItemDiscount will not be persisted
//we only need what we need to compute the bagItem total
public class BagItemDiscount implements IBagItemDIscount {

	private IDiscountableBagItem bagItem;
	
	private Money discountAmount; 
	
	public BagItemDiscount(IDiscountableBagItem bi, Money discount) {
		this.bagItem 		= bi;
		this.discountAmount = discount;
	}

	
	public IDiscountableBagItem getBagItem() {
		return bagItem;
	}
	
	@Override
	public Money getDiscountAmount() {
		return discountAmount;
	}

}
