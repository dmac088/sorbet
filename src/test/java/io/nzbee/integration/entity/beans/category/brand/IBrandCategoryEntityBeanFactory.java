package io.nzbee.integration.entity.beans.category.brand;

import java.util.List;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.integration.entity.beans.IEntityBeanFactory;

public interface IBrandCategoryEntityBeanFactory extends IEntityBeanFactory<CategoryBrandEntity> {
	
	CategoryBrandEntity getBrandCategoryEntityBean();

	List<CategoryBrandEntity> getBrandCategoryEntityListBean();

}
