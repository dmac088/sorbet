package io.nzbee.entity.promotion.order;

import io.nzbee.entity.promotion.PromotionEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionOrderEntity.class)
public abstract class PromotionOrderEntity_ {

	public static volatile SingularAttribute<PromotionOrderEntity, PromotionEntity> orderPromotion;
	public static volatile SingularAttribute<PromotionOrderEntity, String> promotionCouponCode;
	public static volatile SingularAttribute<PromotionOrderEntity, Long> promotionId;

	public static final String ORDER_PROMOTION = "orderPromotion";
	public static final String PROMOTION_COUPON_CODE = "promotionCouponCode";
	public static final String PROMOTION_ID = "promotionId";

}

