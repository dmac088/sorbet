package io.nzbee.domain.promotion;

import java.time.LocalDateTime;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.PromotionCode;

public class Promotion {

	protected final PromotionCode promotionCode;

	protected final LocalDateTime promotionStartDt;

	protected final LocalDateTime promotionEndDt;

	protected final Boolean active;

	protected final Boolean couponRequired;

	protected final CouponCode couponCode;


	public Promotion(PromotionCode promotionCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, CouponCode couponCode) {
		this.promotionCode = promotionCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
	}
	

	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	public LocalDateTime getPromotionStartDt() {
		return promotionStartDt;
	}

	public LocalDateTime getPromotionEndDt() {
		return promotionEndDt;
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

	public CouponCode getCouponCode() {
		return couponCode;
	}


}
