package io.nzbee.entity.promotion.type;

import java.util.Map;

import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionTypeProductDTO implements IPromotionType {

	private PromotionDomainDTO promotion;
	
	public PromotionTypeProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotion = new PromotionDomainDTO(tuple, aliasToIndexMap);
	}
	
	public PromotionDomainDTO getPromotion() {
		return promotion;
	}

	@Override
	public String typeCode() {
		return this.getPromotion().getPromotionType().typeCode();
	} 

}
