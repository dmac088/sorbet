package io.nzbee.integration.entity.promotion.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.integration.entity.promotion.ConfigPromotionEntityTests;
import io.nzbee.util.promotion.category.CategoryPromotionMasterService;

@Configuration 
@Import({ConfigPromotionEntityTests.class,
		 ConfigProductEntityTests.class})
public class ConfigCategoryPromotionEntityTests {
	
	@Bean
	public CategoryPromotionMasterService categoryPromotionMasterService() {
		return new CategoryPromotionMasterService();
	}
	
}
