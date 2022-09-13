package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.valueObjects.ProductUPC;

public class DroolsBagItemWrapper {
	
	private IRegularBagItem bagItem;
	
	public DroolsBagItemWrapper(IRegularBagItem object) {
		this.bagItem = object;
	}
	
	public Long getBagItemQuantity() {
		return this.bagItem.getBagItem().getQuantity();
	}
	
	public Long getBagQuantity() {
		return this.bagItem.getBagItem().getBag().getTotalQuantity();
	}
	
	public BigDecimal getMarkdownPrice() {
		return this.bagItem.getBagItem().getMarkdownPrice().amount();
	}
	
	public String getBagItemStatus() {
		return this.bagItem.getBagItem().getBagItemStatus();
	}
	
	public ProductUPC getProductUPC() {
		return this.bagItem.getBagItem().getProductUPC();
	}
	
	public boolean isInStock() {
		return this.bagItem.isInStock();
	}
	
	public boolean isErrors() {
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
	
	public void addDiscount(IBagItemDiscount discount) {
		this.bagItem.getBagItem().addDiscount(discount);
	}
	
	public void logItemError(String key, IBagItem bagItem) {
		bagItem.getBag().logItemError(key, bagItem);
	}

	public IBagItem getBagItem() {
		return bagItem.getBagItem();
	}
	
	public IBag getBag() {
		return bagItem.getBagItem().getBag();
	}
	
}
