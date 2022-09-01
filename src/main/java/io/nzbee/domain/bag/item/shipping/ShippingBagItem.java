package io.nzbee.domain.bag.item.shipping;

import java.math.BigDecimal;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.promotion.ports.IPromotionPort;

public class ShippingBagItem implements IPromotionPort {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	@Override
	public BigDecimal getTotal() {
		return this.getBagItem().getBagItemTotal();
	}
	
}
