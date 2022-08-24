package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public class Promotion {
	
	private String promotionCode;
	
	private LocalDateTime promotionStartDt;
	
	private LocalDateTime promotionEndDt;
	
	private String promotionMechanicCode;
		
	private String promotionTypeCode;
	
	private String couponCode;
	

	public Promotion(String promotionCode,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 String promotionMechanicCode,
					 String promotionTypeCode) {
		this.promotionCode 			= promotionCode;
		this.promotionStartDt 		= promotionStartDt;
		this.promotionEndDt 		= promotionEndDt;
		this.promotionMechanicCode 	= promotionMechanicCode;
		this.promotionTypeCode 		= promotionTypeCode;
	}
	
	public Promotion(String promotionCode,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 String promotionMechanicCode,
					 String promotionTypeCode,
					 String couponCode) {
		this.promotionCode 			= promotionCode;
		this.promotionStartDt 		= promotionStartDt;
		this.promotionEndDt 		= promotionEndDt;
		this.promotionMechanicCode 	= promotionMechanicCode;
		this.promotionTypeCode 		= promotionTypeCode;	
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
	
	public String getPromotionMechanicCode() {
		return promotionMechanicCode;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public String getCouponCode() {
		return couponCode;
	}
	
}
