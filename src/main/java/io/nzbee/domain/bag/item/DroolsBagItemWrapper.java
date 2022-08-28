package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;

public class DroolsBagItemWrapper {
	
	private RegularBagItem bagItem;
	
	public DroolsBagItemWrapper(RegularBagItem bagItem) {
		this.bagItem = bagItem;
	}
	
	public Long getBagItemQuantity() {
		return this.bagItem.getBagItem().getQuantity();
	}
	
	public int getBagQuantity() {
		return this.bagItem.getBagItem().getBag().getTotalQuantity();
	}
	
	public BigDecimal getMarkdownPrice() {
		return this.bagItem.getBagItem().getMarkdownPrice();
	}
	
	public String getBagItemStatus() {
		return this.bagItem.getBagItem().getBagItemStatus();
	}
	
	public String getProductUPC() {
		return this.bagItem.getBagItem().getProductUPC();
	}
	
	
	public Boolean isInStock() {
		return this.bagItem.isInStock();
	}
	
	public Boolean isErrors() {
		return this.bagItem.getBagItem().isErrors();
	}
	
	public void setErrors(Boolean errors) {
		this.bagItem.getBagItem().setErrors(errors);
	}

	public String getError() {
		return this.bagItem.getBagItem().getError();
	}

	public void setError(String error) {
		this.bagItem.getBagItem().setError(error);
	}
	
	public String getCustomerId() {
		return bagItem.getBagItem().getBag().getCustomer().getCustomerID();
	}
	
	public void addDiscount(BagItemDiscount discount) {
		this.bagItem.getBagItem().addDiscount(discount);
	}
	
	public void logItemError(String key, BagItem bagItem) {
		bagItem.getBag().logItemError(key, bagItem);
	}

	public BagItem getBagItem() {
		return bagItem.getBagItem();
	}
	
	public Bag getBag() {
		return bagItem.getBagItem().getBag();
	}
	
}
