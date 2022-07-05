package io.nzbee.entity.promotion.level;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IPromotionLevelService extends IEntityService<PromotionLevelEntity> {

	Optional<PromotionLevelEntity> findByCode(String code);

	Optional<PromotionLevelEntity> findById(Long id);
	
}
