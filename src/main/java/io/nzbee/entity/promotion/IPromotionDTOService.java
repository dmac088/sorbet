package io.nzbee.entity.promotion;

import java.util.Optional;

public interface IPromotionDTOService  {

	Optional<PromotionDomainDTO> findProductPromotion(String locale, String triggerCode);

	Optional<PromotionDomainDTO> findBagPromotion(String triggerCode);
	
}
