package io.nzbee.integration.entity.beans.category.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.type.CategoryTypeEntity;
import io.nzbee.entity.category.type.ICategoryTypeService;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class BrandCategoryEntityBeanFactory implements IBrandCategoryEntityBeanFactory {


	@Autowired
	private ICategoryTypeService categoryTypeService;
	
	@Override
	public CategoryBrandEntity getBrandCategoryEntityBean() {
		
		CategoryEntity ce = new CategoryEntity();
		ce.setCategoryCode("TST03");
		ce.setCategoryLevel(Long.valueOf(2));
		
		CategoryBrandEntity category = new CategoryBrandEntity();
		
		category.setCategory(ce);
		ce.setCategoryBrand(category);
		
		CategoryTypeEntity ct = categoryTypeService.findByCode(Constants.categoryTypeProduct)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.categoryTypeNotFound, Constants.localeENGB,
						Constants.categoryTypeProduct));
		
		category.getCategory().setCategoryType(ct);
		
		CategoryAttributeEntity categoryAttribute = new CategoryAttributeEntity();
		categoryAttribute.setCategory(category.getCategory());
		categoryAttribute.setCategoryDesc("test brand category");
		categoryAttribute.setLclCd(Constants.localeENGB);
		ce.addCategoryAttribute(categoryAttribute);
		
		return category;
	}
	
	@Override
	public List<CategoryBrandEntity> getBrandCategoryEntityListBean() {
		List<CategoryBrandEntity> lc = new ArrayList<CategoryBrandEntity>();
		
		CategoryBrandEntity category = this.getBrandCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}

	@Override
	public CategoryBrandEntity getBean() {
		return this.getBrandCategoryEntityBean();
	}

}
