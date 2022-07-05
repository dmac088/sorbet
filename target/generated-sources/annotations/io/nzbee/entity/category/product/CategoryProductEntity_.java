package io.nzbee.entity.category.product;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryProductEntity.class)
public abstract class CategoryProductEntity_ {

	public static volatile SingularAttribute<CategoryProductEntity, CategoryEntity> category;
	public static volatile SingularAttribute<CategoryProductEntity, Long> categoryId;
	public static volatile SetAttribute<CategoryProductEntity, ProductEntity> products;

	public static final String CATEGORY = "category";
	public static final String CATEGORY_ID = "categoryId";
	public static final String PRODUCTS = "products";

}

