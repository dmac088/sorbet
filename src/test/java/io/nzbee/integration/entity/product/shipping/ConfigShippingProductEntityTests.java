package io.nzbee.integration.entity.product.shipping;
	
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.integration.entity.beans.product.shipping.IShippingProductEntityBeanFactory;
import io.nzbee.integration.entity.beans.product.shipping.ShippingProductEntityBeanFactory;
import io.nzbee.integration.entity.brand.ConfigBrandEntityTests;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.integration.entity.category.product.ConfigCategoryProductEntityTests;
import io.nzbee.integration.entity.department.ConfigDepartmentEntityTests;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.integration.entity.tag.ConfigTagEntityTests;
import io.nzbee.util.product.shipping.ShippingProductMasterService;

@Configuration
@Import({ConfigProductEntityTests.class, 
		 ConfigBrandEntityTests.class,
		 ConfigDepartmentEntityTests.class,
		 ConfigCategoryEntityTests.class,
		 ConfigCategoryProductEntityTests.class,
		 ConfigPromotionEntityTests.class,
		 ConfigTagEntityTests.class})
public class ConfigShippingProductEntityTests {
	
	@Bean
	public ShippingProductMasterService ShippingProductMasterService() {
		return new ShippingProductMasterService();
	}
	
	@Bean
	public IShippingProductEntityBeanFactory shippingProductEntityBeanFactory() {
		return new ShippingProductEntityBeanFactory();
	}
	
}
