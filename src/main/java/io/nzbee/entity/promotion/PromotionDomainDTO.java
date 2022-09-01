package io.nzbee.entity.promotion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import io.nzbee.Constants;

public class PromotionDomainDTO {

	public static final String CODE_ALIAS = "prm_cd";
	
	public static final String START_DATE_ALIAS = "prm_st_dt";
	
	public static final String END_DATE_ALIAS = "prm_en_dt";
 
	public static final String MECH_CODE_ALIAS = "prm_mec_cd";
	
	public static final String TYPE_CODE_ALIAS = "prm_typ_cd";
	
	public static final String ACTIVE_ALIAS = "prm_act";
	
	
	
	private final String promotionCode;
	
	private final LocalDateTime promotionStartDate;
	
	private final LocalDateTime promotionEndDate;
	
	private final String promotionMechanicCode;
	
	private final String promotionTypeCode;
	
	private final Boolean promotionIsActive;
	
	
	public PromotionDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionCode 				= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionStartDate 		= LocalDateTime.parse(tuple[aliasToIndexMap.get(START_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionEndDate			= LocalDateTime.parse(tuple[aliasToIndexMap.get(END_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionMechanicCode 		= tuple[aliasToIndexMap.get(MECH_CODE_ALIAS)].toString();
		this.promotionTypeCode			= tuple[aliasToIndexMap.get(TYPE_CODE_ALIAS)].toString();
		this.promotionIsActive 			= ((Boolean) tuple[aliasToIndexMap.get(ACTIVE_ALIAS)]);
	}

	public String getPromotionCode() {
		return promotionCode;
	}


	public LocalDateTime getPromotionStartDate() {
		return promotionStartDate;
	}

	public LocalDateTime getPromotionEndDate() {
		return promotionEndDate;
	}
	
	public String getPromotionMechanicCode() {
		return promotionMechanicCode;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}


	public Boolean getPromotionIsActive() {
		return promotionIsActive;
	}
	
	public Boolean isBagType() {
		return promotionTypeCode.equals(Constants.promotionTypeBag);
	}
	
	public Boolean isProductType() {
		return promotionTypeCode.equals(Constants.promotionTypeProduct);
	}
	
	public Boolean isShippingType() {
		return promotionTypeCode.equals(Constants.promotionTypeShipping);
	}
		
}
