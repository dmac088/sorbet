package io.nzbee.domain.bag.item.shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;

public class ShippingBagItem implements IDiscountThresholdPromotionPort, IDiscountableBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	@Override
	public List<IDiscountableBagItem> getItems() {
		List<IDiscountableBagItem> sbi = new ArrayList<IDiscountableBagItem>();
		sbi.add(this);
		return sbi;
	}

	@Override
	public String getUPC() {
		return this.getBagItem().getProductUPC();
	}

	@Override
	public BigDecimal getTotalAmount() {
		return this.getBagItem().getBagItemTotal();
	}

	@Override
	public BigDecimal getBagTotalAmount() {
		return this.getBagItem().getBag().getSubTotalAmount();
	}
	
}
