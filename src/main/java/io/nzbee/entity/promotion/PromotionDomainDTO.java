package io.nzbee.entity.promotion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import io.nzbee.entity.promotion.type.IPromotionType;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionDomainDTO implements IPromotionDTO {

	public static final String CODE_ALIAS = "prm_cd";
	
	public static final String START_DATE_ALIAS = "prm_st_dt";
	
	public static final String END_DATE_ALIAS = "prm_en_dt";
 
	public static final String MECH_CODE_ALIAS = "prm_mec_cd";
	
	public static final String ACTIVE_ALIAS = "prm_act";
	
	public static final String COUPON_REQUIRED_ALIAS = "prm_trg_rq";
	
	public static final String COUPON_CODE_ALIAS = "prm_trg_cd";
	
	public static final String TYPE_CODE_ALIAS = "prm_typ_cd";
	
	
	private final String promotionCode;
	
	private final LocalDateTime promotionStartDate;
	
	private final LocalDateTime promotionEndDate;
	
	private final String promotionMechanicCode;
	
	private final Boolean promotionIsActive;
	
	private final Boolean couponRequired;
	
	private final String couponCode;
	
	private final String promotionTypeCode;
	
	private IPromotionType promotionType;
	
	
	public PromotionDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionCode 				= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionStartDate 		= LocalDateTime.parse(tuple[aliasToIndexMap.get(START_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionEndDate			= LocalDateTime.parse(tuple[aliasToIndexMap.get(END_DATE_ALIAS)].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		this.promotionMechanicCode 		= tuple[aliasToIndexMap.get(MECH_CODE_ALIAS)].toString();
		this.couponRequired 			= ((Boolean) tuple[aliasToIndexMap.get(ACTIVE_ALIAS)]);
		this.promotionIsActive 			= ((Boolean) tuple[aliasToIndexMap.get(COUPON_REQUIRED_ALIAS)]);
		this.couponCode					= tuple[aliasToIndexMap.get(COUPON_CODE_ALIAS)].toString();
		this.promotionTypeCode 			= tuple[aliasToIndexMap.get(TYPE_CODE_ALIAS)].toString();
		this.promotionType				= PromotionDTOResultTransformer.getType(tuple[aliasToIndexMap.get(TYPE_CODE_ALIAS)].toString(), tuple, aliasToIndexMap);
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

	public Boolean getPromotionIsActive() {
		return promotionIsActive;
	}
	
	public Boolean getCouponRequired() {
		return couponRequired;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public IPromotionType getPromotionType() {
		return promotionType;
	}

	@Override
	public String getTypeCode() {
		return this.promotionTypeCode;
	}

	@Override
	public String getMechanicCode() {
		return this.promotionMechanicCode;
	}

	@Override
	public Boolean isType(String typeCode) {
		return this.promotionTypeCode.equals(typeCode);
	}
	
}
