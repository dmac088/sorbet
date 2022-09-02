package io.nzbee.domain.bag.item.shipping;

import java.util.ArrayList;
import java.util.List;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;

public class ShippingBagItem implements IDiscountThresholdPromotionPort, IBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	@Override
	public List<IBagItem> getItems() {
		List<IBagItem> sbi = new ArrayList<IBagItem>();
		sbi.add(this);
		return sbi;
	}

	@Override
	public String getUPC() {
		return this.getBagItem().getProductUPC();
	}
	
}
