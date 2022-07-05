package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IPromotionEntityService extends IEntityService<PromotionEntity> {

	Optional<PromotionEntity> findByDesc(String promotionDesc);

	Optional<PromotionEntity> findByCode(String promotionCode);

	Optional<PromotionEntity> findById(Long promotionId);

	Optional<PromotionDomainDTO> findByCode(String locale, String code);
	
}
