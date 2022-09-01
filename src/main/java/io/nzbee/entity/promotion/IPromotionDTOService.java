package io.nzbee.entity.promotion;

import java.util.List;

public interface IPromotionDTOService  {

	List<PromotionDomainDTO> findShippingPromotion();
	
	List<PromotionDomainDTO> findItemPromotion(String itemUPC);

	List<PromotionDomainDTO> findBagPromotions();

}
