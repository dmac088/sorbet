package io.nzbee.entity.promotion;

import java.util.List;

import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public interface IPromotionDTOService  {

	List<IPromotionDTO> findItemPromotion(String itemUPC);

	List<IPromotionDTO> findBagPromotions();

	List<IPromotionDTO> findShippingPromotions();

}
