package io.nzbee.entity.promotion.order;

import java.time.LocalDateTime;
import java.util.Map;

import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionOrderDTO extends PromotionDomainDTO {

	public static final String COUPON_CODE_ALIAS = "prm_cpn_cd";
	
	private String couponCode;
	
	public PromotionOrderDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.couponCode = tuple[aliasToIndexMap.get(COUPON_CODE_ALIAS)].toString();
	}
	
	public PromotionOrderDTO(
								String promotionCode, 
								String promotionMechanicCode,
								String promotionTypeCode,
								LocalDateTime promotionStartDate,
								LocalDateTime promotionEndDate, 
								String couponCode) {
		super(
			  promotionCode,
			  promotionMechanicCode,
			  promotionTypeCode,
			  promotionStartDate,
			  promotionEndDate);
		
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

}
