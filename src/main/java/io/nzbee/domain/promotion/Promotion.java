package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public class Promotion {
	
	protected final String promotionCode;
	
	protected final String promotionTypeCode;
	
	protected final LocalDateTime promotionStartDt;
	
	protected final LocalDateTime promotionEndDt;
	
	protected final Boolean active;
	
	protected final Boolean couponRequired;
	
	protected final String couponCode;

	
	public Promotion(String promotionCode,
					 String promotionTypeCode,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 Boolean active,
					 Boolean couponRequired,
					 String couponCode) {
		this.promotionCode 			= promotionCode;
		this.promotionTypeCode		= promotionTypeCode;
		this.promotionStartDt 		= promotionStartDt;
		this.promotionEndDt 		= promotionEndDt;
		this.active					= active;
		this.couponRequired			= couponRequired;
		this.couponCode 			= couponCode;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public LocalDateTime getPromotionStartDt() {
		return promotionStartDt;
	}

	public LocalDateTime getPromotionEndDt() {
		return promotionEndDt;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}
	
	public Boolean validate() {
		return true;
	}
	
	public Boolean getActive() {
		return active;
	}

	public Boolean getCouponRequired() {
		return couponRequired;
	}

	public String getCouponCode() {
		return couponCode;
	}
	
}
