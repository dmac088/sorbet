package io.nzbee.integration.entity.category.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.category.product.CategoryProductDaoImpl;
import io.nzbee.entity.category.product.CategoryProductServiceImpl;
import io.nzbee.entity.category.product.ICategoryProductDao;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.integration.entity.beans.category.product.IProductCategoryEntityBeanFactory;
import io.nzbee.integration.entity.beans.category.product.ProductCategoryEntityBeanFactory;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;

@Configuration
@Import(ConfigCategoryEntityTests.class)
public class ConfigCategoryProductEntityTests {
	
	@Bean
	public ICategoryProductDao categoryProductDao() {
		return new CategoryProductDaoImpl();
	}
	
	@Bean ICategoryProductService categoryProductService() {
		return new CategoryProductServiceImpl();
	}
	
	@Bean 
	public IProductCategoryEntityBeanFactory productCategoryEntityBeanFactory() {
		return new ProductCategoryEntityBeanFactory();
	}
	
}
