package io.nzbee.entity.promotion.type;

import java.util.Map;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionTypeBrandDTO implements IPromotionType  {
	
	public static final String BRAND_CODE_ALIAS = "prm_bnd_cd";
	
	private PromotionDomainDTO promotion;
	
	private final String brandCode;
	
	public PromotionTypeBrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotion = new PromotionDomainDTO(tuple, aliasToIndexMap);
		this.brandCode = tuple[aliasToIndexMap.get(BRAND_CODE_ALIAS)].toString();
	}
	
	public PromotionDomainDTO getPromotion() {
		return promotion;
	}


	public String getBrandCode() {
		return brandCode;
	}

	public void setPromotion(PromotionDomainDTO promotion) {
		this.promotion = promotion;
	}
	
	@Override
	public String typeCode() {
		return this.getPromotion().getPromotionType().typeCode();
	} 


}
