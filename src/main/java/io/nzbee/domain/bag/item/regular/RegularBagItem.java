package io.nzbee.domain.bag.item.regular;

import java.math.BigDecimal;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.promotion.ports.IBnGnFreePromotionPort;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;

public class RegularBagItem implements IDiscountThresholdPromotionPort, IBnGnFreePromotionPort {
	
	private final BagItem bagItem;
	
	private final BigDecimal weight;
	
	private final Boolean inStock;

	public RegularBagItem(BagItem bagItem, BigDecimal weight, Boolean inStock) {
		super();
		this.bagItem = bagItem;
		this.weight = weight;
		this.inStock = inStock;
	}
	

	public BigDecimal getBagItemWeight() {
		 return this.weight;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	public boolean isInStock() {
		return inStock;
	}

	public BigDecimal getWeight() {
		return weight;
	}


	@Override
	public BigDecimal getTotalAmount() {
		return this.getTotalAmount();
	}


	@Override
	public Long getTotalQuantity() {
		return this.getTotalQuantity();
	}
}
