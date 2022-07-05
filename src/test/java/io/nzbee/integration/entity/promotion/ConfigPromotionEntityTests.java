package io.nzbee.integration.entity.promotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.promotion.PromotionEntityServiceImpl;
import io.nzbee.entity.promotion.IPromotionDao;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.util.promotion.product.ProductPromotionMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigPromotionEntityTests {
	
	@Bean 
	public IPromotionEntityService PromotionServiceImpl() {
		return new PromotionEntityServiceImpl();
	}
	
	@Bean 
	public IPromotionDao PromotionDao() {
		return new PromotionDaoPostgresImpl();
	}
	
	@Bean
	public ProductPromotionMasterService productPromotionMasterService() {
		return new ProductPromotionMasterService();
	}
}
