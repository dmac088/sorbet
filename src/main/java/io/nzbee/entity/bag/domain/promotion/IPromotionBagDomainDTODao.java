package io.nzbee.entity.bag.domain.promotion;

import java.util.Optional;

public interface IPromotionBagDomainDTODao {

	Optional<PromotionBagDomainDTO> findByCode(String locale, String currency, String username);
	
}
