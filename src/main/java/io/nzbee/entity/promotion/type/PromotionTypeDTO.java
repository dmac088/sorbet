package io.nzbee.entity.promotion.type;

import java.util.Map;

public class PromotionTypeDTO implements IPromotionType {
	
	private final String PROMOTION_TYPE_CODE_ALIAS = "prm_typ_cd";

	private final String promotionTypeCode;
	
	public PromotionTypeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super();
		this.promotionTypeCode 				= tuple[aliasToIndexMap.get(PROMOTION_TYPE_CODE_ALIAS)].toString();
	//	this.promotionTypeDesc 				= tuple[aliasToIndexMap.get(PROMOTION_TYPE_DESC_ALIAS)].toString();
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}


	@Override
	public String typeCode() {
		return this.getPromotionTypeCode();
	}

}
