package io.nzbee;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;
import io.nzbee.unit.domain.beans.promotion.PromotionDoBeanFactory;


@Configuration
public class UT_Config {

	@Bean 
    public RestTemplate unitTestTemplate() {
		return new RestTemplate();
    }
	
	@Bean
	public CustomerDoBeanFactory customerDoBeanFactory() {
		return new CustomerDoBeanFactory();
	}
	
	@Bean
	public PromotionDoBeanFactory PromotionDoBeanFactory() {
		return new PromotionDoBeanFactory();
	}
	
	@Bean 
	public KieServices kieServices() {
		return KieServices.Factory.get();
	}
	
	@Bean 
	public BagItemConfiguration bagItemConfiguration() {
		return new BagItemConfiguration();
	}
	
	@Bean 
	public KieContainer kieConfiguration() {
		return bagItemConfiguration().kieContainer();
	}
	

}
