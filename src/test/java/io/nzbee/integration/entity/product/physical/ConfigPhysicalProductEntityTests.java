package io.nzbee.integration.entity.product.physical;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.adapters.util.PhysicalProductMasterAdapter;
import io.nzbee.integration.entity.brand.ConfigBrandEntityTests;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.integration.entity.category.product.ConfigCategoryProductEntityTests;
import io.nzbee.integration.entity.department.ConfigDepartmentEntityTests;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.integration.entity.tag.ConfigTagEntityTests;
import io.nzbee.util.ports.IPhysicalProductMasterPort;
import io.nzbee.util.product.physical.PhysicalProductMasterService;

@Configuration
@Import({ConfigProductEntityTests.class, 
		 ConfigBrandEntityTests.class,
		 ConfigDepartmentEntityTests.class,
		 ConfigCategoryEntityTests.class,
		 ConfigCategoryProductEntityTests.class,
		 ConfigPromotionEntityTests.class,
		 ConfigTagEntityTests.class})
public class ConfigPhysicalProductEntityTests {
	
	@Bean
	public PhysicalProductMasterService physicalProductMasterService() {
		return new PhysicalProductMasterService();
	}
	
	@Bean
	public IPhysicalProductMasterPort physicalProductMasterPort() {
		return new PhysicalProductMasterAdapter();
	}
	
}
