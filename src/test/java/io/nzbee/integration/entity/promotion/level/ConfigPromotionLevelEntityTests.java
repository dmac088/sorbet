package io.nzbee.integration.entity.promotion.level;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.promotion.level.IPromotionLevelEntityBeanFactory;
import io.nzbee.integration.entity.beans.promotion.level.PromotionLevelEntityBeanFactory;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigPromotionLevelEntityTests {

	@Bean
	public IPromotionLevelEntityBeanFactory promotionLevelEntityBeanFactory() {
		return new PromotionLevelEntityBeanFactory();
	}
	
}
