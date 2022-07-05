package io.nzbee.integration.entity.product.status;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.product.status.IProductStatusEntityBeanFactory;
import io.nzbee.integration.entity.beans.product.status.ProductStatusEntityBeanFactory;

@Configuration
@Import({ConfigEntityTests.class})
public class ConfigProductStatusEntityTests {
	
	@Bean
	public IProductStatusEntityBeanFactory productStatusEntityBeanFactory() {
		return new ProductStatusEntityBeanFactory();
	}
	
}
