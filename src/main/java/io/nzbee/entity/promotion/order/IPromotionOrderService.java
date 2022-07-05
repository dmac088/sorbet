package io.nzbee.entity.promotion.order;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IPromotionOrderService extends IEntityService<PromotionOrderEntity> {
	
	Optional<PromotionOrderDTO> findByCouponCode(String locale, String couponCode);
	
	Optional<PromotionOrderDTO> findByCode(String locale, String code);

	Optional<PromotionOrderEntity> findByCode(String code);

	Optional<PromotionOrderEntity> findById(Long id);
	
}
