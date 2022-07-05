package io.nzbee.integration.entity.beans.category.product;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;


@Service
public class ProductCategoryEntityBeanFactory implements IProductCategoryEntityBeanFactory {

	@Override
	public final CategoryProductEntity getProductCategoryEntityBean() {
		
		CategoryEntity ce = new CategoryEntity();
		CategoryProductEntity category = new CategoryProductEntity();
		
		category.setCategory(ce);
		ce.setCategoryProduct(category);
	
		category.getCategory().setCategoryCode("TST02");
		category.getCategory().setCategoryLevel(Long.valueOf(1));

		final CategoryAttributeEntity categoryAttributeEn = new CategoryAttributeEntity();
		categoryAttributeEn.setCategory(category.getCategory());
		categoryAttributeEn.setCategoryDesc("test product category");
		categoryAttributeEn.setLclCd(Constants.localeENGB);
		category.getCategory().addCategoryAttribute(categoryAttributeEn);
		
		final CategoryAttributeEntity categoryAttributeCn = new CategoryAttributeEntity();
		categoryAttributeCn.setCategory(category.getCategory());
		categoryAttributeCn.setCategoryDesc("測試產品類別");
		categoryAttributeCn.setLclCd(Constants.localeZHHK);
		category.getCategory().addCategoryAttribute(categoryAttributeCn);
		
		return category;
	}

	@Override
	public final List<CategoryProductEntity> getProductCategoryEntityListBean() {
		List<CategoryProductEntity> lc = new ArrayList<CategoryProductEntity>();
		
		final CategoryProductEntity category = this.getProductCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}

	@Override
	public CategoryProductEntity getBean() {
		return this.getProductCategoryEntityBean();
	}

	
	
}
