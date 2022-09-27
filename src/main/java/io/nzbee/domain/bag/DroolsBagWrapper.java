package io.nzbee.domain.bag;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.valueObjects.Money;

public class DroolsBagWrapper {
	
	private IBag bag;
	
	public DroolsBagWrapper(IBag bag) {
		this.bag = bag;
	}
	
	public void logItemError(String key, BagItem bag) {
		bag.getBag().logItemError(key, bag);
	}
	
	public int getTotalItems() {
		return bag.getTotalItems();
	}
	
	public Money getTotalAmount() {
		return bag.getSubTotalAmount();
	}

}
