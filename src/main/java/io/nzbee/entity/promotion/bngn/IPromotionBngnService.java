package io.nzbee.entity.promotion.bngn;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IPromotionBngnService extends IEntityService<PromotionBngnEntity> {
	
	Optional<PromotionBngnDTO> findByCouponCode(String locale, String couponCode);
	
	Optional<PromotionBngnDTO> findByCode(String locale, String code);

	Optional<PromotionBngnEntity> findByCode(String code);

	Optional<PromotionBngnEntity> findById(Long id);
	
}
