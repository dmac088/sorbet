package io.nzbee.integration.entity.category.brand;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.category.brand.CategoryBrandDaoImpl;
import io.nzbee.entity.category.brand.CategoryBrandServiceImpl;
import io.nzbee.entity.category.brand.ICategoryBrandDao;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.integration.entity.beans.category.brand.BrandCategoryEntityBeanFactory;
import io.nzbee.integration.entity.beans.category.brand.IBrandCategoryEntityBeanFactory;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;

@Configuration
@Import(ConfigCategoryEntityTests.class)
public class ConfigCategoryBrandEntityTests {
	
	@Bean
	public ICategoryBrandDao categoryBrandDao() {
		return new CategoryBrandDaoImpl();
	}
	
	@Bean 
	public ICategoryBrandService categoryBrandService() {
		return new CategoryBrandServiceImpl();
	}
	
	@Bean 
	public IBrandCategoryEntityBeanFactory brandCategoryEntityBeanFactory() {
		return new BrandCategoryEntityBeanFactory();
	}
	
}
