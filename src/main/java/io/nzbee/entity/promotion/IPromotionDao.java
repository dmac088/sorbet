package io.nzbee.entity.promotion;

import java.util.Optional;

public interface IPromotionDao {
	
	Optional<PromotionDomainDTO> findProductPromotion(String itemUPC, String triggerCode);

	Optional<PromotionDomainDTO> findBagPromotion(String triggerCode);
	
}

