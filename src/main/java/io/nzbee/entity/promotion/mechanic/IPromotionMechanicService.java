package io.nzbee.entity.promotion.mechanic;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IPromotionMechanicService extends IEntityService<PromotionMechanicEntity> {

	Optional<PromotionMechanicEntity> findByDesc(String promotionMechanicDesc);

	Optional<PromotionMechanicEntity> findByCode(String promotionMechanicCode);

	Optional<PromotionMechanicEntity> findById(Long promotionMechanicId);
	
}
