package io.nzbee.entity.promotion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PromotionDomainDTO {

	public static final String CODE_ALIAS = "prm_cd";
	
	public static final String START_DATE_ALIAS = "prm_st_dt";
	
	public static final String END_DATE_ALIAS = "prm_en_dt";
 
	public static final String MECH_CODE_ALIAS = "prm_mec_cd";
	
	public static final String TYPE_CODE_ALIAS = "prm_typ_cd";
	
	public static final String ACTIVE_ALIAS = "prm_act";
	
	
	private String promotionCode;
	
	private LocalDateTime promotionStartDate;
	
	private LocalDateTime promotionEndDate;
	
	private String promotionMechanicCode;
	
	private String promotionTypeCode;
	
	private Boolean promotionIsActive;
	
	
	public PromotionDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionCode 				= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionStartDate 		= LocalDateTime.parse(tuple[aliasToIndexMap.get(START_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionEndDate			= LocalDateTime.parse(tuple[aliasToIndexMap.get(END_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionMechanicCode 		= tuple[aliasToIndexMap.get(MECH_CODE_ALIAS)].toString();
		this.promotionTypeCode			= tuple[aliasToIndexMap.get(TYPE_CODE_ALIAS)].toString();
		this.promotionIsActive 			= ((Boolean) tuple[aliasToIndexMap.get(ACTIVE_ALIAS)]);
	}
	
	
	public PromotionDomainDTO(
						String promotionCode,
						String promotionMechanicCode,
						String promotionTypeCode,
						LocalDateTime promotionStartDate,
						LocalDateTime promotionEndDate) {
		super();
		this.promotionCode 			= promotionCode;
		this.promotionMechanicCode	= promotionMechanicCode;
		this.promotionTypeCode		= promotionTypeCode;
		this.promotionStartDate 	= promotionStartDate;
		this.promotionEndDate 		= promotionEndDate;
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

	public void setPromotionIsActive(Boolean promotionIsActive) {
		this.promotionIsActive = promotionIsActive;
	}
		
}
