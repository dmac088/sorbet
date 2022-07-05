package io.nzbee.integration.entity.brand;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.brand.BrandServiceImpl;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.view.facet.BrandFacetDTODaoImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.brand.BrandEntityBeanFactory;
import io.nzbee.integration.entity.beans.brand.IBrandEntityBeanFactory;
import io.nzbee.util.brand.BrandMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigBrandEntityTests {

	@Bean
	public IBrandEntityBeanFactory brandEntityBeanFactory() {
		return new BrandEntityBeanFactory();
	}
	
	@Bean 
	public BrandMasterService brandMasterService() {
		return new BrandMasterService();
	}
	
	@Bean 
	public IBrandService brandServiceImpl() {
		return new BrandServiceImpl();
	}
	
	@Bean 
	public BrandFacetDTOServiceImpl brandFacetDTOServiceImpl() {
		return new BrandFacetDTOServiceImpl();
	}
	
	@Bean
	public BrandFacetDTODaoImpl brandFacetDTODaoImpl() {
		return new BrandFacetDTODaoImpl();
	}
	
}
