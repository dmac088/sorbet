package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;

public class DroolsBagItemWrapper {
	
	private IRegularBagItem bagItem;
	
	public DroolsBagItemWrapper(IRegularBagItem object) {
		this.bagItem = object;
	}
	
	public Long getBagItemQuantity() {
		return this.bagItem.getBagItem().getQuantity().amount();
	}
	
	public BigDecimal getMarkdownPrice() {
		return this.bagItem.getBagItem().getMarkdownPrice().amount();
	}
	
	public String getBagItemStatus() {
		return this.bagItem.getBagItem().getBagItemStatus().toString();
	}
	
	public String getProductUPC() {
		return this.bagItem.getBagItem().getProductUPC().toString();
	}
	
	public boolean isInStock() {
		return this.bagItem.isInStock();
	}
	
	public boolean isErrors() {
		return this.bagItem.getBagItem().getBag().isErrors();
	}
	
	public void setErrors(boolean errors) {
		this.bagItem.getBagItem().getBag().setErrors(errors);
	}

	public String getError() {
		return this.bagItem.getBagItem().getBag().getError();
	}

	public void setError(String error) {
		this.bagItem.getBagItem().getBag().setError(error);
	}
	
	public String getCustomerId() {
		return bagItem.getBagItem().getBag().getCustomer().getCustomerID();
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