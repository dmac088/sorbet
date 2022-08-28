package io.nzbee.domain.bag.item;

import java.math.BigDecimal;

//remember the BagItemDiscount will not be persisted
//we only need what we need to compute the bagItem total
public class BagItemDiscount {

	private BagItem bagItem;
	
	private BigDecimal discountAmount; 
	
	public BagItemDiscount(BagItem bagItem, BigDecimal discount) {
		this.bagItem 		= bagItem;
		this.discountAmount = discount;
	}

	public BagItem getBagItem() {
		return bagItem;
	}
	
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

}
