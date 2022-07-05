package io.nzbee.entity.category.brand;

import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.CategoryEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryBrandEntity.class)
public abstract class CategoryBrandEntity_ {

	public static volatile SetAttribute<CategoryBrandEntity, BrandEntity> brands;
	public static volatile SingularAttribute<CategoryBrandEntity, CategoryEntity> category;
	public static volatile SingularAttribute<CategoryBrandEntity, Long> categoryId;

	public static final String BRANDS = "brands";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_ID = "categoryId";

}

