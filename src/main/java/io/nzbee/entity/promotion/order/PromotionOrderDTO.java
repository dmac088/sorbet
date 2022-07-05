package io.nzbee.entity.promotion.order;

import java.time.LocalDateTime;
import java.util.Map;

import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionOrderDTO extends PromotionDomainDTO {

	private static final long serialVersionUID = -4596062542753000601L;

	public static final String COUPON_CODE_ALIAS = "prm_cpn_cd";
	
	private String couponCode;
	
	public PromotionOrderDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		
		this.couponCode = tuple[aliasToIndexMap.get(COUPON_CODE_ALIAS)].toString();
	}
	
	public PromotionOrderDTO(	Long   promotionId, 
								String promotionCode, 
								String promotionDesc, 
								Long promotionMechanicId,
								String promotionMechanicCode,
								String promotionMechanicDesc,
								Long promotionTypeId,
								String promotionTypeCode,
								String promotionTypeDesc,
								LocalDateTime promotionStartDate,
								LocalDateTime promotionEndDate, 
								String locale,
								String couponCode) {
		super(promotionId,
			  promotionCode,
			  promotionDesc,
			  promotionMechanicId,
			  promotionMechanicCode,
			  promotionMechanicDesc,
			  promotionTypeId,
			  promotionTypeCode,
			  promotionTypeDesc,
			  promotionStartDate,
			  promotionEndDate,
			  locale);
		
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

	
	
}
