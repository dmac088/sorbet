package io.nzbee.entity.promotion.type;

import java.util.Map;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionTypeBrandDTO implements IPromotionTypeBrandDTO  {
	
	public static final String BRAND_CODE_ALIAS = "prm_bnd_cd";
	
	private IPromotionDTO promotion;
	
	private final String brandCode;
	
	public PromotionTypeBrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.brandCode = tuple[aliasToIndexMap.get(BRAND_CODE_ALIAS)].toString();
	}
	
	public IPromotionDTO getPromotion() {
		return promotion;
	}
	
	@Override
	public String getBrandCode() {
		return brandCode;
	}

	@Override
	public String typeCode() {
		return Constants.promotionTypeBrand;
	}

	@Override
	public void setPromotion(IPromotionDTO promotionDTO) {
		this.promotion = promotionDTO;
	}

}
