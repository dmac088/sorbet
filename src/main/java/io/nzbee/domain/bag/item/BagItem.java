package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;

public class BagItem implements IBagItem {

	private Bag bag;
	
	private final List<CategoryCode> categoryCodes;
	
	private final BrandCode brandCode;
	
	private final ProductUPC productUPC;
	
	private Long quantity;
	
	private Boolean errors = false;
	
	private String error;
	
	private String bagItemStatus;
	
	private final Money markdownPrice;
	
	private final List<IBagItemDiscount> discounts;

	
	public BagItem(	Bag bag, 
					ProductUPC productUPC,
			  	   	Long quantity,
			  	   	Money markdownPrice,
			  	   	BrandCode brandCode,
			  	   	List<CategoryCode> categoryCodes) {
		this.bag 				= bag;
		this.productUPC			= productUPC;
		this.quantity 			= quantity;
		this.bagItemStatus 		= Constants.bagItemStatusCodeNew;
		this.markdownPrice 		= markdownPrice;
		this.discounts 			= new ArrayList<IBagItemDiscount>();
		this.brandCode			= brandCode;
		this.categoryCodes  	= categoryCodes;
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

	public ProductUPC getProductUPC() {
		return this.productUPC;
	}
	
	public Money getMarkdownPrice() {
		return markdownPrice;
	}
	
	public Money getBagItemSubTotal() {
		return this.markdownPrice.multiply(this.quantity);
	}
	
	@Override
	public Money getBagItemTotal() {
		return this.markdownPrice.multiply(this.quantity).subtract(this.getBagItemDiscountTotal());
	}

	public boolean isErrors() {
		return errors;
	}

	@Override
	public void setErrors(Boolean errors) {
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
	
	@Override
	public Money getBagItemDiscountTotal() {
		BigDecimal b = this.getDiscounts().stream().map(d -> d.getDiscountAmount().amount()).reduce(BigDecimal.ZERO, BigDecimal::add);
		return bag.getMoney().add(new Money(b, bag.getCurrency(), BigDecimal.ROUND_HALF_UP));
	}
	
	public void addDiscount(IBagItemDiscount discount) {
		this.discounts.add(discount);
	}

	public List<IBagItemDiscount> getDiscounts() {
		return discounts;
	}

	public List<CategoryCode> getCategoryCodes() {
		return categoryCodes;
	}

	public BrandCode getBrandCode() {
		return brandCode;
	}

	
}
