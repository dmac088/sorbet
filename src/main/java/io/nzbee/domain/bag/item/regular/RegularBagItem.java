package io.nzbee.domain.bag.item.regular;

import java.math.BigDecimal;
import io.nzbee.domain.bag.item.BagItem;

public class RegularBagItem {
	
	private final BagItem bagItem;
	
	private final BigDecimal weight;
	
	private final boolean inStock;

	public RegularBagItem(BagItem bagItem, BigDecimal weight, boolean inStock) {
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
}
