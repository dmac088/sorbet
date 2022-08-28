package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;

public class BagItem {

	private Bag bag;
	
	private String productUPC;
	
	private Long quantity;
	
	private boolean errors;
	
	private String error;
	
	private String bagItemStatus;
	
	private BigDecimal markdownPrice;
	
	private List<BagItemDiscount> discounts;

	
	public BagItem(	Bag bag, 
					String productUPC,
			  	   	Long quantity,
			  	   	BigDecimal markdownPrice) {
		this.bag 				= bag;
		this.productUPC			= productUPC;
		this.quantity 			= quantity;
		this.bagItemStatus 		= Constants.bagItemStatusCodeNew;
		this.markdownPrice 		= markdownPrice;
		this.discounts 			= new ArrayList<BagItemDiscount>();
	}

	public Bag getBag() {
		return bag;
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public void addToQuantity(Long quantity) {
		this.quantity += quantity;
	}

	public String getProductUPC() {
		return this.productUPC;
	}
	
	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}
	
	public BigDecimal getBagItemTotal() {
		return new BigDecimal(this.quantity).multiply((this.markdownPrice).subtract(this.getBagItemDiscount()));
	}

	public boolean isErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setBagItemStatus(String bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}

	public String getBagItemStatus() {
		return bagItemStatus;
	}
	
	public BigDecimal getBagItemDiscount() {
		return new BigDecimal(this.discounts.stream().mapToDouble(d -> d.getDiscountAmount().doubleValue()).sum());
	}
	
	public void addDiscount(BagItemDiscount discount) {
		this.discounts.add(discount);
	}

	public List<BagItemDiscount> getDiscounts() {
		return discounts;
	}
	
}
