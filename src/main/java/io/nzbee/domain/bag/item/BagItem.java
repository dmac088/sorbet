package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.discount.Discount;
import io.nzbee.domain.promotion.Promotion;

public class BagItem {

	private Bag bag;
	
	private String productUPC;
	
	private int quantity;
	
	private boolean errors;
	
	private String error;
	
	private String bagItemStatus;
	
	private BigDecimal markdownPrice;
	
	private List<Discount> discounts;
	
	private List<Promotion> promotions;
	
	public BagItem(	Bag bag, 
					String productUPC,
			  	   	int quantity,
			  	   	BigDecimal markdownPrice) {
		this.bag 				= bag;
		this.productUPC			= productUPC;
		this.quantity 			= quantity;
		this.bagItemStatus 		= Constants.bagItemStatusCodeNew;
		this.markdownPrice 		= markdownPrice;
		this.discounts 			= new ArrayList<Discount>();
		this.promotions			= new ArrayList<Promotion>();
	}

	public Bag getBag() {
		return bag;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void addToQuantity(int quantity) {
		this.quantity += quantity;
	}

	public String getProductUPC() {
		return this.productUPC;
	}
	
	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}
	
	public BigDecimal getBagItemTotal() {
		return new BigDecimal(this.quantity).multiply(this.markdownPrice);///* - this.getBagItemDiscount().doubleValue()*/);
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
	
	public Double getBagItemDiscount() {
		return this.discounts.stream().mapToDouble(d -> d.getDiscountAmount()).sum();
	}
	
	public void addDiscount(Discount discount) {
		this.discounts.add(discount);
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void addPromotion(Promotion promotion) {
		this.promotions.add(promotion);
	}
	
}
