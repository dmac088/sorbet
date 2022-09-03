package io.nzbee.domain.bag.item.shipping;

import java.math.BigDecimal;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;

public class ShippingBagItem implements IDiscountableBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
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

	@Override
	public void addDiscount(BagItemDiscount discountItem) {
		this.getBagItem().addDiscount(discountItem);
	}

}
