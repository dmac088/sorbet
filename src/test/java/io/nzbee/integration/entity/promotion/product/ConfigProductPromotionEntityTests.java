package io.nzbee.integration.entity.promotion.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.promotion.level.IPromotionLevelService;
import io.nzbee.entity.promotion.level.PromotionLevelServiceImpl;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicServiceImpl;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.integration.entity.promotion.type.ConfigPromotionTypeEntityTests;
import io.nzbee.util.promotion.regular.PromotionProductMasterService;

@Configuration
@Import({ConfigPromotionEntityTests.class,
		 ConfigPromotionTypeEntityTests.class,
		 ConfigProductEntityTests.class})
public class ConfigProductPromotionEntityTests {
	
	@Bean
	public PromotionProductMasterService promotionProductMasterService() {
		return new PromotionProductMasterService();
	}
	
	@Bean
	public IPromotionMechanicService promotionMechanicService() {
		return new PromotionMechanicServiceImpl();
	}
	
	@Bean
	public IPromotionLevelService promotionLevelService() {
		return new PromotionLevelServiceImpl();
	}
}
