package io.nzbee.entity.promotion.type;

import java.util.Map;

import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionTypeNullDTO implements IPromotionType  {
	
	private IPromotionDTO promotion;
	
	public PromotionTypeNullDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
	}
	
	public IPromotionDTO getPromotion() {
		return promotion;
	}
	
	@Override
	public String typeCode() {
		return "NULL";
	}

	@Override
	public void setPromotion(IPromotionDTO promotionDTO) {
		this.promotion = promotionDTO;
	} 
}
