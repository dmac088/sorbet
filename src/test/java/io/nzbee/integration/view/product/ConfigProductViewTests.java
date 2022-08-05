package io.nzbee.integration.view.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.Globals;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.integration.entity.product.physical.light.ConfigPhysicalProductLightEntityTests;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.IFacetServices;
import io.nzbee.search.ISearchService;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.view.ports.IPhysicalProductLightPortService;

@Configuration
//we need to import other configuration classes to create the spring context 
//we cannot arbitrarily create beans in this config class since the configuration classes are 
//not simply services or components but actual configurations, also we don't want to component scan
//io.nzbee.* since this will load things (services, components, configs) we don't want into the spring context
@Import({DataSourceBeanMochi.class,
		 DataSourceBeanSecurity.class,
		 FileStorageProperties.class,
		 FileStorageServiceUpload.class,
		 Globals.class,
		 ConfigPhysicalProductLightEntityTests.class,
		 })

public class ConfigProductViewTests {
	
	@Bean 
	IPhysicalProductLightPortService physicalProductLightPortService() {
		return new PhysicalProductLightAdapterImpl();
	}
	
	@Bean
	ISearchService searchService() {
		return new SearchServiceImpl();
	}
	
	@Bean
	IFacetServices facetServices() {
		return new FacetServicesImpl();
	}
	
}
