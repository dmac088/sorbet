package io.nzbee.domain.promotion.item;

import java.math.BigDecimal;

public class DiscountItem {

	private final String promotionCode;
	
	private final String upcCode;
	
	private final BigDecimal discountAmount;

	public DiscountItem(String promotionCode, 
						String upcCode,
						BigDecimal discountAmount) {
		super();
		this.promotionCode = promotionCode;
		this.upcCode = upcCode;
		this.discountAmount = discountAmount;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
}
