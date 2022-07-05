package io.nzbee.integration.entity.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.entity.product.currency.CurrencyServiceImpl;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.physical.entity.IPhysicalProductEntityService;
import io.nzbee.entity.product.physical.entity.PhysicalProductEntityServiceImpl;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTODao;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOService;
import io.nzbee.entity.product.physical.view.PhysicalProductDTODaoPostgresImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOServiceImpl;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;
import io.nzbee.integration.entity.beans.product.physical.PhysicalProductEntityBeanFactory;
import io.nzbee.integration.entity.brand.ConfigBrandEntityTests;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.integration.entity.department.ConfigDepartmentEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.integration.entity.tag.ConfigTagEntityTests;

@Configuration
@Import({ConfigEntityTests.class, 
		 ConfigBrandEntityTests.class,
		 ConfigDepartmentEntityTests.class,
		 ConfigCategoryEntityTests.class,
		 ConfigPromotionEntityTests.class,
		 ConfigTagEntityTests.class})
public class ConfigProductEntityTests {
	
	@Bean 
	public IPhysicalProductEntityService physicalProductEntityService() {
		return new PhysicalProductEntityServiceImpl();
	}
	
	@Bean
	public IPhysicalProductEntityBeanFactory physicalProductEntityBeanFactory() {
		return new PhysicalProductEntityBeanFactory();
	}
	
	@Bean
	public IPhysicalProductDTOService productService() {
		return new PhysicalProductDTOServiceImpl();
	}
	
	@Bean 
	public ICurrencyService currencyService() {
		return new CurrencyServiceImpl();
	}
	
	@Bean 
	public IProductPriceTypeService productPriceTypeService() {
		return new ProductPriceTypeService();
	}
	
	@Bean
	public IProductService productEntityService() {
		return new ProductEntityServiceImpl();
	}
	
	@Bean
	public IPhysicalProductDTODao physicalProductDao() {
		return new PhysicalProductDTODaoPostgresImpl();
	}
	
}
