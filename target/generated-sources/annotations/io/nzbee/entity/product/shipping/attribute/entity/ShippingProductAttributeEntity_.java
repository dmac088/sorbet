package io.nzbee.entity.product.shipping.attribute.entity;

import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductAttributeEntity.class)
public abstract class ShippingProductAttributeEntity_ {

	public static volatile SingularAttribute<ShippingProductAttributeEntity, ShippingProductEntity> product;
	public static volatile SingularAttribute<ShippingProductAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<ShippingProductAttributeEntity, Long> Id;
	public static volatile SingularAttribute<ShippingProductAttributeEntity, String> shippingTypeDesc;
	public static volatile SingularAttribute<ShippingProductAttributeEntity, String> shippingCountryDesc;

	public static final String PRODUCT = "product";
	public static final String LCL_CD = "lclCd";
	public static final String ID = "Id";
	public static final String SHIPPING_TYPE_DESC = "shippingTypeDesc";
	public static final String SHIPPING_COUNTRY_DESC = "shippingCountryDesc";

}

