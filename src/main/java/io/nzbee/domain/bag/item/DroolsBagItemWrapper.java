package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.discount.Discount;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.promotion.Promotion;

public class DroolsBagItemWrapper {
	
	private RegularBagItem bagItem;
	
	public DroolsBagItemWrapper(RegularBagItem bagItem) {
		this.bagItem = bagItem;
	}
	
	public int getBagItemQuantity() {
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
	
	public List<String> getPromotionCodes() {
		return this.bagItem.getBagItem().getPromotions().stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
	}
	
	public List<Promotion> getAllPromotions() {
		return this.bagItem.getBagItem().getPromotions();
	}
	
	public Promotion getPromotion(String promotionCode) {
		return this.bagItem.getBagItem().getPromotions().stream().filter(p -> p.getPromotionCode().equals(promotionCode)).findAny().get();
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
	
	public void addDiscount(Discount discount) {
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
