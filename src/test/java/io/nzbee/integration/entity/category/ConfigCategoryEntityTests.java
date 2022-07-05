package io.nzbee.integration.entity.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductServiceImpl;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTODao;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOService;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTODaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.util.category.CategoryMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigCategoryEntityTests {
	
	@Bean
	public CategoryMasterService categoryMasterService() {
		return new CategoryMasterService();
	}
	
	@Bean
	public ICategoryService categoryService() {
		return new CategoryServiceImpl();
	}
		
	@Bean 
	public IProductCategoryFacetDTOService productCategoryFacetDTOService() {
		return new ProductCategoryFacetDTOServiceImpl();
	}
	
	@Bean
	public IProductCategoryFacetDTODao productCategoryFacetDTODao() {
		return new ProductCategoryFacetDTODaoImpl();
	}
	
	@Bean
	public ICategoryProductService categoryProductService() {
		return new CategoryProductServiceImpl();
	}
}
