package io.nzbee.entity.promotion;

import java.util.Optional;

public interface IPromotionDTOService  {

	Optional<PromotionDomainDTO> find(String locale, String triggerCode);

	Optional<PromotionDomainDTO> find(String triggerCode);

}
