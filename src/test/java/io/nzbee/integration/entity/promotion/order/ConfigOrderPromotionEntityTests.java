package io.nzbee.integration.entity.promotion.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.integration.entity.beans.promotion.level.IPromotionLevelEntityBeanFactory;
import io.nzbee.integration.entity.beans.promotion.level.PromotionLevelEntityBeanFactory;
import io.nzbee.integration.entity.promotion.product.ConfigProductPromotionEntityTests;
import io.nzbee.util.promotion.order.PromotionOrderMasterService;

@Configuration
@Import({ConfigProductPromotionEntityTests.class})
public class ConfigOrderPromotionEntityTests {

	@Bean
	public IPromotionLevelEntityBeanFactory promotionLevelEntityBeanFactory() {
		return new PromotionLevelEntityBeanFactory();
	}
	
	@Bean
	public PromotionOrderMasterService promotionOrderMasterService() {
		return new PromotionOrderMasterService();
	}
	
}
