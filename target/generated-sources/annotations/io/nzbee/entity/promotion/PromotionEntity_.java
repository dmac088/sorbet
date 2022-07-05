package io.nzbee.entity.promotion;

import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.entity.promotion.order.PromotionOrderEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionEntity.class)
public abstract class PromotionEntity_ {

	public static volatile SingularAttribute<PromotionEntity, PromotionTypeEntity> promotionType;
	public static volatile SingularAttribute<PromotionEntity, PromotionLevelEntity> promotionLevel;
	public static volatile SingularAttribute<PromotionEntity, LocalDateTime> promotionEndDate;
	public static volatile SingularAttribute<PromotionEntity, Boolean> promotionActive;
	public static volatile SingularAttribute<PromotionEntity, String> promotionCode;
	public static volatile SingularAttribute<PromotionEntity, PromotionProductEntity> promotionProduct;
	public static volatile SetAttribute<PromotionEntity, PromotionAttributeEntity> attributes;
	public static volatile SingularAttribute<PromotionEntity, LocalDateTime> promotionStartDate;
	public static volatile SingularAttribute<PromotionEntity, PromotionMechanicEntity> promotionMechanic;
	public static volatile SingularAttribute<PromotionEntity, Long> promotionId;
	public static volatile SingularAttribute<PromotionEntity, PromotionOrderEntity> promotionOrder;

	public static final String PROMOTION_TYPE = "promotionType";
	public static final String PROMOTION_LEVEL = "promotionLevel";
	public static final String PROMOTION_END_DATE = "promotionEndDate";
	public static final String PROMOTION_ACTIVE = "promotionActive";
	public static final String PROMOTION_CODE = "promotionCode";
	public static final String PROMOTION_PRODUCT = "promotionProduct";
	public static final String ATTRIBUTES = "attributes";
	public static final String PROMOTION_START_DATE = "promotionStartDate";
	public static final String PROMOTION_MECHANIC = "promotionMechanic";
	public static final String PROMOTION_ID = "promotionId";
	public static final String PROMOTION_ORDER = "promotionOrder";

}

