package io.nzbee.integration.entity.product.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.cache.CacheConfiguration;
import io.nzbee.integration.entity.product.physical.light.ConfigPhysicalProductLightEntityTests;

@Configuration
@Import({ConfigPhysicalProductLightEntityTests.class,
		 CacheConfiguration.class})
public class ConfigProductCacheEntityTests {
	

	
}
