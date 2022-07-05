package io.nzbee.unit.domain.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.nzbee.unit.domain.beans.promotion.IPromotionDoBeanFactory;
import io.nzbee.unit.domain.beans.promotion.PromotionDoBeanFactory;

@Configuration
public class ConfigProductDomainObjectTests {
	
	@Bean
	public IPromotionDoBeanFactory promotionDoBeanFactory() {
		return new PromotionDoBeanFactory();
	}
	
}
