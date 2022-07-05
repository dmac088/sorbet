package io.nzbee.entity.product.shipping.entity;

import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.shipping.attribute.entity.ShippingProductAttributeEntity;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductEntity.class)
public abstract class ShippingProductEntity_ {

	public static volatile SingularAttribute<ShippingProductEntity, String> shippingTypeCode;
	public static volatile SingularAttribute<ShippingProductEntity, String> shippingCountryCode;
	public static volatile SingularAttribute<ShippingProductEntity, ProductEntity> shippingProduct;
	public static volatile SingularAttribute<ShippingProductEntity, Long> productId;
	public static volatile SingularAttribute<ShippingProductEntity, BigDecimal> weightTo;
	public static volatile SetAttribute<ShippingProductEntity, ShippingProductAttributeEntity> attributes;
	public static volatile SingularAttribute<ShippingProductEntity, BigDecimal> weightLimit;
	public static volatile SingularAttribute<ShippingProductEntity, BigDecimal> weightFrom;

	public static final String SHIPPING_TYPE_CODE = "shippingTypeCode";
	public static final String SHIPPING_COUNTRY_CODE = "shippingCountryCode";
	public static final String SHIPPING_PRODUCT = "shippingProduct";
	public static final String PRODUCT_ID = "productId";
	public static final String WEIGHT_TO = "weightTo";
	public static final String ATTRIBUTES = "attributes";
	public static final String WEIGHT_LIMIT = "weightLimit";
	public static final String WEIGHT_FROM = "weightFrom";

}

