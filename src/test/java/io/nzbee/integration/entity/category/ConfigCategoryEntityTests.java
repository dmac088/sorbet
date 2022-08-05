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
import io.nzbee.entity.category.type.CategoryTypeServiceImpl;
import io.nzbee.entity.category.type.ICategoryTypeService;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.util.category.CategoryMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigCategoryEntityTests {
	
	@Bean
	ICategoryTypeService CategoryTypeService() {
		return new CategoryTypeServiceImpl();
	}
	
	@Bean
	CategoryMasterService categoryMasterService() {
		return new CategoryMasterService();
	}
	
	@Bean
	ICategoryService categoryService() {
		return new CategoryServiceImpl();
	}
		
	@Bean 
	IProductCategoryFacetDTOService productCategoryFacetDTOService() {
		return new ProductCategoryFacetDTOServiceImpl();
	}
	
	@Bean
	IProductCategoryFacetDTODao productCategoryFacetDTODao() {
		return new ProductCategoryFacetDTODaoImpl();
	}
	
	@Bean
	ICategoryProductService categoryProductService() {
		return new CategoryProductServiceImpl();
	}
}
