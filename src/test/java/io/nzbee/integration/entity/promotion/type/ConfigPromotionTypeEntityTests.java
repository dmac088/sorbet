package io.nzbee.integration.entity.promotion.type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.promotion.type.IPromotionTypeEntityBeanFactory;
import io.nzbee.integration.entity.beans.promotion.type.PromotionTypeEntityBeanFactory;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigPromotionTypeEntityTests {
	
	@Bean
	public IPromotionTypeEntityBeanFactory promotionTypeEntityBeanFactory() {
		return new PromotionTypeEntityBeanFactory();
	}
	
	@Bean
	public IPromotionTypeService promotionTypeService() {
		return new PromotionTypeServiceImpl();
	}
}
