package io.nzbee.entity.promotion.type;

import java.util.Map;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionTypeCategoryDTO implements IPromotionType  {
	
	public static final String CATEGORY_CODE_ALIAS = "prm_cat_cd";
	
	private PromotionDomainDTO promotion;
	
	private final String categoryCode;
	
	public PromotionTypeCategoryDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotion = new PromotionDomainDTO(tuple, aliasToIndexMap);
		this.categoryCode = tuple[aliasToIndexMap.get(CATEGORY_CODE_ALIAS)].toString();
	}
	
	public PromotionDomainDTO getPromotion() {
		return promotion;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setPromotion(PromotionDomainDTO promotion) {
		this.promotion = promotion;
	}
	
	@Override
	public String typeCode() {
		return this.getPromotion().getPromotionType().typeCode();
	} 

}
