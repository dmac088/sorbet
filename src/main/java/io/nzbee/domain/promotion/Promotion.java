package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public class Promotion implements IProductPromotionType {

	protected final String promotionCode;

	protected final String promotionTypeCode;

	protected final LocalDateTime promotionStartDt;

	protected final LocalDateTime promotionEndDt;

	protected final Boolean active;

	protected final Boolean couponRequired;

	protected final String couponCode;

	private String productUPC;

	private String brandCode;

	private String categoryCode;

	public Promotion(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
	}

	public Promotion(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode,
			String productUPC) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
		this.productUPC = productUPC;
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

	@Override
	public String getUPC() {
		return this.productUPC;
	}

	@Override
	public Boolean forUPC(String upc) {
		return this.productUPC.equals(upc);
	}

}
