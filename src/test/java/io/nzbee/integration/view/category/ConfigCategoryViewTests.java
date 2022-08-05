package io.nzbee.integration.view.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.Globals;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOMapper;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOMapperImpl;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.integration.entity.party.address.ConfigPartyAddressEntityTests;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.view.ports.ICategoryViewPortService;

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
		 ConfigPartyAddressEntityTests.class,
		 ConfigCategoryEntityTests.class
		 })

public class ConfigCategoryViewTests {
	
	@Bean
	ICategoryViewPortService categoryViewPortService() {
		return new ProductCategoryAdapterImpl();
	}
	
	@Bean
	IProductCategoryFacetDTOMapper productCategoryFacetDTOMapper() {
		return new ProductCategoryFacetDTOMapperImpl();
	}
}
