package io.nzbee.entity.promotion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import io.nzbee.entity.promotion.type.PromotionTypeDTO;

public class PromotionViewDTO {

	public static final String ID_ALIAS = "prm_id";
	
	public static final String CODE_ALIAS = "prm_cd";
	
	public static final String DESC_ALIAS = "prm_desc";
	
	public static final String START_DATE_ALIAS = "prm_st_dt";
	
	public static final String END_DATE_ALIAS = "prm_en_dt";
	
	
	private Long promotionId;
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private LocalDateTime promotionStartDate;
	
	private LocalDateTime promotionEndDate;
	
	private String locale;
	
	protected PromotionTypeDTO typeDTO;
	
	
	public PromotionViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.promotionCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionDesc 		= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.promotionStartDate = LocalDateTime.parse(tuple[aliasToIndexMap.get(START_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionEndDate	= LocalDateTime.parse(tuple[aliasToIndexMap.get(END_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
	}
	
	public Long getPromotionId() {
		return promotionId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public String getLocale() {
		return locale;
	}

	public LocalDateTime getPromotionStartDate() {
		return promotionStartDate;
	}

	public LocalDateTime getPromotionEndDate() {
		return promotionEndDate;
	}

	public PromotionTypeDTO getTypeDTO() {
		return typeDTO;
	}

	public void setTypeDTO(PromotionTypeDTO typeDTO) {
		this.typeDTO = typeDTO;
	}

}
