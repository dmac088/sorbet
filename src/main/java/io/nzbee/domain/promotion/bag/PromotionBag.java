package io.nzbee.domain.promotion.bag;

import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionBag {
	
	private final List<PromotionItem> promotionItems;
	
	private final List<CouponCode> coupons; 
	
	private final UserName userName;
	
	private final Locale locale;
	
	public PromotionBag(UserName userName, Locale locale) {
		super();
		this.locale = locale;
		this.promotionItems = new ArrayList<PromotionItem>();
		this.coupons = new ArrayList<CouponCode>();
		this.userName = userName;
	}


	public List<PromotionItem> getPromotionItems() {
		return promotionItems;
	}
	
	public void addPromotionItem(PromotionItem promotionItem) {
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
		
}
