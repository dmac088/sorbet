package io.nzbee.entity.promotion.type;

import java.util.Map;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionTypeCategoryDTO implements IPromotionTypeCategoryDTO  {
	
	public static final String CATEGORY_CODE_ALIAS = "prm_cat_cd";
	
	private IPromotionDTO promotion;
	
	private final String categoryCode;
	
	public PromotionTypeCategoryDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.categoryCode = tuple[aliasToIndexMap.get(CATEGORY_CODE_ALIAS)].toString();
	}
	
	public IPromotionDTO getPromotion() {
		return promotion;
	}

	@Override
	public String getCategoryCode() {
		return categoryCode;
	}

	@Override
	public String typeCode() {
		return Constants.promotionTypeCategory;
	}

	@Override
	public void setPromotion(IPromotionDTO promotionDTO) {
		this.promotion = promotionDTO;
	} 

}
