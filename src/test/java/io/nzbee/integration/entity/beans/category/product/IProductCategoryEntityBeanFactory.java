package io.nzbee.integration.entity.beans.category.product;

import java.util.List;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.integration.entity.beans.IEntityBeanFactory;

public interface IProductCategoryEntityBeanFactory extends IEntityBeanFactory<CategoryProductEntity> {

	CategoryProductEntity getProductCategoryEntityBean();

	List<CategoryProductEntity> getProductCategoryEntityListBean();

}
