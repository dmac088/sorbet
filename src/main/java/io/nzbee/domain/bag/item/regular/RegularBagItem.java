package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.valueObjects.Weight;

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
	public IBag getBag() {
		return this.bagItem.getBag();
	}
}
