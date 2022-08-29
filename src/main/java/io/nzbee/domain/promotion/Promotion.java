package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public abstract class Promotion {
	
	protected final String promotionCode;
	
	protected final String promotionTypeCode;
	
	protected final LocalDateTime promotionStartDt;
	
	protected final LocalDateTime promotionEndDt;

	
	public Promotion(String promotionCode,
					 String promotionTypeCode,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt) {
		this.promotionCode 			= promotionCode;
		this.promotionTypeCode		= promotionTypeCode;
		this.promotionStartDt 		= promotionStartDt;
		this.promotionEndDt 		= promotionEndDt;
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
	
	public abstract void compute();
	
}
