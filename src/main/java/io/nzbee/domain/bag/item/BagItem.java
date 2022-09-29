package io.nzbee.domain.bag.item;

import io.nzbee.Constants;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public class BagItem implements IBagItem {

	private IBag bag;
	
	private final ProductUPC productUPC;
	
	private Quantity quantity;
	
	private BagItemStatus bagItemStatus;
	
	private final Money markdownPrice;
	
	public BagItem(	IBag bag, 
					ProductUPC productUPC,
			  	   	Quantity quantity,
			  	   	Money markdownPrice) {
		this.bag 				= bag;
		this.productUPC			= productUPC;
		this.quantity 			= quantity;
		this.bagItemStatus 		= new BagItemStatus(Constants.bagItemStatusCodeNew);
		this.markdownPrice 		= markdownPrice;
	}

	public IBag getBag() {
		return bag;
	}

	public Quantity getQuantity() {
		return quantity;
	}
	
	public void addToQuantity(Quantity quantity) {
		this.quantity = this.quantity.add(quantity);
	}

	public ProductUPC getProductUPC() {
		return this.productUPC;
	}
	
	public Money getMarkdownPrice() {
		return markdownPrice;
	}
	
	public void setBagItemStatus(BagItemStatus bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}

	public BagItemStatus getBagItemStatus() {
		return bagItemStatus;
	}

}
