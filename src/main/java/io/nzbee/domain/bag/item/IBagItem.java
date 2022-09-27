package io.nzbee.domain.bag.item;

import java.util.List;

import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;

public interface IBagItem {

	Long getQuantity();

	IBag getBag();

	Money getMarkdownPrice();

	String getBagItemStatus();

	ProductUPC getProductUPC();

	void addDiscount(IBagItemDiscount discount);

	Money getBagItemSubTotal();
	
	Money getBagItemTotal();

	void addToQuantity(Long qty);

	List<IBagItemDiscount> getDiscounts();

	Money getBagItemDiscountTotal();

	void setBagItemStatus(String string);

	

}
