package io.nzbee.entity.promotion;

import java.util.Optional;


public interface IPromotionDao {
	
	Optional<PromotionEntity> findByCode(String code);

	Optional<PromotionDomainDTO> findByCode(String locale, String code);
}
