package io.nzbee.domain.bag.item.shipping;

import java.util.List;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;

public class ShippingBagItem implements IDiscountableBagItem, IShippingBagItem {

	private final BagItem bagItem;

	public ShippingBagItem(BagItem bagItem) {
		super();
		this.bagItem = bagItem;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	@Override
	public ProductUPC getUPC() {
		return this.getBagItem().getProductUPC();
	}

	@Override
	public Money getTotalAmount() {
		return this.getBagItem().getBagItemTotal();
	}

	@Override
	public Money getBagTotalAmount() {
		return this.getBagItem().getBag().getSubTotalAmount();
	}

	@Override
	public void addDiscount(BagItemDiscount discountItem) {
		this.getBagItem().addDiscount(discountItem);
	}

	@Override
	public BrandCode getBrandCode() {
		return this.getBagItem().getBrandCode();
	}

	@Override
	public List<CategoryCode> getCategoryCodes() {
		return this.getBagItem().getCategoryCodes();
	}

	@Override
	public Long getQuantity() {
		return this.getBagItem().getQuantity();
	}
	
	@Override
	public Money getPrice() {
		return this.getBagItem().getMarkdownPrice();
	}

	@Override
	public String getUserName() {
		return this.getBagItem().getBag().getCustomer().getUserName();
	}
}
