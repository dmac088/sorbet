package io.nzbee.entity.promotion;

import java.util.List;

public interface IPromotionDao {
	
	List<PromotionDomainDTO> findShippingPromotion();

	List<PromotionDomainDTO> findBagPromotions();
	
	List<PromotionDomainDTO> findItemPromotion(String itemUPC);

}

