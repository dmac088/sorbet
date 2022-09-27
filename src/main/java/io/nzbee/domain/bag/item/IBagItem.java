package io.nzbee.domain.bag.item;

import java.util.List;

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

	void addDiscount(IBagItemDiscount discount);

	Money getBagItemSubTotal();
	
	Money getBagItemTotal();

	void addToQuantity(Quantity qty);

	List<IBagItemDiscount> getDiscounts();

	Money getBagItemDiscountTotal();

	void setBagItemStatus(BagItemStatus string);

	

}
