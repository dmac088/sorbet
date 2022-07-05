package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.item.BagItem;

public class ShippingBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}
	
}
