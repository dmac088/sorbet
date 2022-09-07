package io.nzbee.domain.bag.item.regular;

import java.math.BigDecimal;
import java.util.List;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;

public class RegularBagItem implements IDiscountableBagItem {

	private final BagItem bagItem;

	private final BigDecimal weight;

	private final Boolean inStock;

	public RegularBagItem(BagItem bagItem, BigDecimal weight, Boolean inStock) {
		super();
		this.bagItem = bagItem;
		this.weight = weight;
		this.inStock = inStock;
	}

	public BigDecimal getBagItemWeight() {
		return this.weight;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	public boolean isInStock() {
		return inStock;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	@Override
	public Money getTotalAmount() {
		return this.getBagItem().getBagItemTotal();
	}

	@Override
	public ProductUPC getUPC() {
		return this.getBagItem().getProductUPC();
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

}
