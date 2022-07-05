package io.nzbee.entity.promotion.product;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.promotion.PromotionEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionProductEntity.class)
public abstract class PromotionProductEntity_ {

	public static volatile SingularAttribute<PromotionProductEntity, PromotionEntity> productPromotion;
	public static volatile SetAttribute<PromotionProductEntity, CategoryEntity> categories;
	public static volatile SingularAttribute<PromotionProductEntity, Long> promotionId;
	public static volatile SetAttribute<PromotionProductEntity, ProductEntity> products;

	public static final String PRODUCT_PROMOTION = "productPromotion";
	public static final String CATEGORIES = "categories";
	public static final String PROMOTION_ID = "promotionId";
	public static final String PRODUCTS = "products";

}

