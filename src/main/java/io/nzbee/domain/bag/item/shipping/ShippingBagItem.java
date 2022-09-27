package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.valueObjects.UserName;

public class ShippingBagItem implements IShippingBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	@Override
	public UserName getUserName() {
		return this.getBagItem().getBag().getCustomer().getUserName();
	}
}
