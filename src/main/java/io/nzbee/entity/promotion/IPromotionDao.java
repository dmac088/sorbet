package io.nzbee.entity.promotion;

import java.util.List;

import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public interface IPromotionDao {
	

	List<IPromotionDTO> findBagPromotions();
	
	List<IPromotionDTO> findItemPromotion(String itemUPC);

	List<IPromotionDTO> findShippingPromotions();

}

