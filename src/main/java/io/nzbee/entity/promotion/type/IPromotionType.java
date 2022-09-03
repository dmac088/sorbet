package io.nzbee.entity.promotion.type;

import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public interface IPromotionType {

	String typeCode();

	void setPromotion(IPromotionDTO promotionDTO);
	
}
