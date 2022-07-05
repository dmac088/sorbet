package io.nzbee.integration.entity.promotion.mechanic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.util.promotion.mechanic.PromotionMechanicMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigPromotionMechanicEntityTests {

	@Bean
	public PromotionMechanicMasterService promotionMechanicMasterService() {
		return new PromotionMechanicMasterService();
	}
	
	@Bean
	public IPromotionMechanicService promotionMechanicService() {
		return new PromotionMechanicServiceImpl();
	}
}
