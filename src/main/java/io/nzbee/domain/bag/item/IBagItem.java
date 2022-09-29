package io.nzbee.domain.bag.item;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public interface IBagItem {

	Quantity getQuantity();

	IBag getBag();

	Money getMarkdownPrice();

	BagItemStatus getBagItemStatus();

	ProductUPC getProductUPC();

	void addToQuantity(Quantity qty);

	void setBagItemStatus(BagItemStatus string);	

}
