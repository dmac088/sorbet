package io.nzbee.entity.promotion.order;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionOrderRepository extends JpaRepository<PromotionOrderEntity, Long> {

	List<PromotionOrderEntity> findAll();
	
	Optional<PromotionOrderEntity> findByOrderPromotionPromotionCode(String promotionCode);
	
	@Query(
			"SELECT new io.nzbee.entity.promotion.order.PromotionOrderDTO (" +
			" op.promotionId," +
			" op.promotionCode," +
			" attr.promotionDesc," +
			" pm.promotionMechanicId, " +
			" pm.promotionMechanicCode, " +
			" pm.promotionMechanicDesc, " +
			" pt.promotionTypeId, " +
			" pt.promotionTypeCode, " +
			" pt.promotionTypeDesc, " +
			" op.promotionStartDate," +
			" op.promotionEndDate," +
			" attr.locale, " +
			" pce.promotionCouponCode" +
			") " +
			"FROM PromotionOrderEntity pce " +
			"JOIN pce.orderPromotion op " +
			"JOIN op.attributes attr " + 
			"JOIN op.promotionMechanic pm " +
			"JOIN op.promotionType pt " +
			"WHERE op.promotionCode = :promotionCode " + 
			"AND attr.locale = :locale "		
	)
	Optional<PromotionOrderDTO> findByCode(String locale, String promotionCode);
	
	@Query(
			"SELECT new io.nzbee.entity.promotion.order.PromotionOrderDTO (" +
			" op.promotionId," +
			" op.promotionCode," +
			" attr.promotionDesc," +
			" pm.promotionMechanicId, " +
			" pm.promotionMechanicCode, " +
			" pm.promotionMechanicDesc, " +
			" pt.promotionTypeId, " +
			" pt.promotionTypeCode, " +
			" pt.promotionTypeDesc, " +
			" op.promotionStartDate," +
			" op.promotionEndDate," +
			" attr.locale, " +
			" pce.promotionCouponCode" +
			") " +
			"FROM PromotionOrderEntity pce " +
			"JOIN pce.orderPromotion op " +
			"JOIN op.attributes attr " + 
			"JOIN op.promotionMechanic pm " +
			"JOIN op.promotionType pt " +
			"WHERE pce.promotionCouponCode = :couponCode " + 
			"AND attr.locale = :locale "		
	)
	Optional<PromotionOrderDTO> findByPromotionCouponCode(String locale, String couponCode);
	
}

