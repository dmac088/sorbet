package io.nzbee.integration.entity.product.physical.full;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.product.physical.full.IPhysicalProductFullDTOService;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullDao;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDTOServiceImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDaoImpl;
import io.nzbee.integration.entity.brand.ConfigBrandEntityTests;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.integration.entity.department.ConfigDepartmentEntityTests;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.integration.entity.tag.ConfigTagEntityTests;

@Configuration
@Import({	ConfigProductEntityTests.class, 
			ConfigBrandEntityTests.class, 
			ConfigDepartmentEntityTests.class, 
			ConfigCategoryEntityTests.class, 
			ConfigPromotionEntityTests.class,
			ConfigTagEntityTests.class})
public class ConfigPhysicalProductFullEntityTests {
	
	@Bean 
	public IPhysicalProductFullDTOService physicalProductFullDTOService() {
		return new PhysicalProductFullDTOServiceImpl();
	}
	
	@Bean 
	public IPhysicalProductFullDao physicalProductFullDao() {
		return new PhysicalProductFullDaoImpl();
	}
	
}
