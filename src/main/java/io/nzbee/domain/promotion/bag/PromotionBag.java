package io.nzbee.domain.promotion.bag;

import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.promotion.bag.item.PromotionBagItem;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionBag implements IPromotionBag {
	
	private final List<PromotionBagItem> promotionItems;
	
	private final List<CouponCode> coupons; 
	
	private final UserName userName;
	
	private final Locale locale;
	
	private final Quantity quantity;
	
	private final int itemCount;
	
	private Boolean errors = false;
	
	private String error;
	
	public PromotionBag(UserName userName, Locale locale, Quantity quantity, int itemCount) {
		super();
		this.locale = locale;
		this.promotionItems = new ArrayList<PromotionBagItem>();
		this.coupons = new ArrayList<CouponCode>();
		this.userName = userName;
		this.quantity = quantity;
		this.itemCount = itemCount;
	}

	public List<PromotionBagItem> getPromotionItems() {
		return promotionItems;
	}
	
	public void addPromotionItem(PromotionBagItem promotionItem) {
		this.promotionItems.add(promotionItem);
	}

	public List<CouponCode> getCoupons() {
		return coupons;
	}
	
	public void addCoupon(CouponCode couponCode) {
		if(!this.getCoupons().contains(couponCode)) {
			this.getCoupons().add(couponCode);
		}
	}

	public UserName getUserName() {
		return userName;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public Quantity getQuantity() {
		return this.quantity;
	}

	@Override
	public int getItemCount() {
		return itemCount;
	}
	
	@Override
	public Boolean isErrors() {
		return errors;
	}

	@Override
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}

	@Override
	public String getError() {
		return error;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}
		
}
