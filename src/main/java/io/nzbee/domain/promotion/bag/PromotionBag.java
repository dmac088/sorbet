package io.nzbee.domain.promotion.bag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.Constants;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionBag implements IPromotionBag {
	
	private final List<IPromotionBagItem> promotionItems;
	
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
		this.promotionItems = new ArrayList<IPromotionBagItem>();
		this.coupons = new ArrayList<CouponCode>();
		this.userName = userName;
		this.quantity = quantity;
		this.itemCount = itemCount;
	}

	public void addPromotionItem(IPromotionBagItem promotionItem) {
		this.promotionItems.add(promotionItem);
	}

	@Override
	public List<CouponCode> getCoupons() {
		return coupons;
	}
	
	public void addCoupon(CouponCode couponCode) {
		if(!this.getCoupons().contains(couponCode)) {
			this.getCoupons().add(couponCode);
		}
	}

	@Override
	public UserName getUserName() {
		return userName;
	}

	@Override
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
	
	public Money getMoney() {
		return new Money(BigDecimal.ZERO, this.getLocale().getCurrency(), Constants.defaultMoneyRounding);
	}
	
	@Override
	public Money getTotalAmount() {
		Money sum = this.getMoney();
        for (IPromotionBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getTotalAmount());
        }
		return sum;
	}

	@Override
	public List<IPromotionBagItem> getBagItems() {
		return promotionItems;
	}
		
}
