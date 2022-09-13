package io.nzbee.domain.bag.item.regular;

import java.util.List;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.Weight;

public class RegularBagItem implements IRegularBagItem {

	private final BagItem bagItem;

	private final Weight weight;

	private final Boolean inStock;

	public RegularBagItem(BagItem bagItem, Weight weight, Boolean inStock) {
		super();
		this.bagItem = bagItem;
		this.weight = weight;
		this.inStock = inStock;
	}

	public Weight getBagItemWeight() {
		return this.weight;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

	public Boolean isInStock() {
		return inStock;
	}

	public Weight getWeight() {
		return weight; 
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
