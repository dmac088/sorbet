package io.nzbee.domain.bag.item;

import java.math.BigDecimal;

//remember the BagItemDiscount will not be persisted
//we only need what we need to compute the bagItem total
public class BagItemDiscount {

	private IDiscountableBagItem bagItem;
	
	private BigDecimal discountAmount; 
	
	public BagItemDiscount(IDiscountableBagItem bi, BigDecimal discount) {
		this.bagItem 		= bi;
		this.discountAmount = discount;
	}

	public IDiscountableBagItem getBagItem() {
		return bagItem;
	}
	
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

}
