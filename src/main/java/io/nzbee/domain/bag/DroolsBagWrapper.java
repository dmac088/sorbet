package io.nzbee.domain.bag;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.promotion.value.Money;

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
	
	public Money getTotalAmount() {
		return bag.getSubTotalAmount();
	}

}
