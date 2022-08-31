package io.nzbee.domain.bag;

import java.math.BigDecimal;

import io.nzbee.domain.bag.item.BagItem;

public class DroolsBagWrapper {
	
	private Bag bag;
	
	public DroolsBagWrapper(Bag bag) {
		this.bag = bag;
	}
	
	public void logItemError(String key, BagItem bag) {
		bag.getBag().logItemError(key, bag);
	}
	
	public int getTotalItems() {
		return bag.getTotalItems();
	}
	
	public BigDecimal getTotalAmount() {
		return bag.getSubTotalAmount();
	}

}
