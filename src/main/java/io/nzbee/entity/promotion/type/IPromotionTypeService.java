package io.nzbee.entity.promotion.type;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IPromotionTypeService extends IEntityService<PromotionTypeEntity> {

	Optional<PromotionTypeEntity> findByCode(String code);

	Optional<PromotionTypeEntity> findById(Long id);
	
}
