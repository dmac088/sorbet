package io.nzbee.integration.entity.product.physical.light;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightDTOService;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightDao;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTOServiceImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;

@Configuration
@Import({ConfigProductEntityTests.class})
public class ConfigPhysicalProductLightEntityTests {
	
	@Bean 
	public IPhysicalProductLightDTOService physicalProductLightDTOService() {
		return new PhysicalProductLightDTOServiceImpl();
	}
	
	@Bean 
	public IPhysicalProductLightDao physicalProductLightDao() {
		return new PhysicalProductLightDaoImpl();
	}
	
}
