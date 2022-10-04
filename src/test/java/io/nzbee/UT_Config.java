package io.nzbee;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;


@Configuration
public class UT_Config {

	@Bean 
    RestTemplate unitTestTemplate() {
		return new RestTemplate();
    }
	
	@Bean
	CustomerDoBeanFactory customerDoBeanFactory() {
		return new CustomerDoBeanFactory();
	}
	
	@Bean 
	KieServices kieServices() {
		return KieServices.Factory.get();
	}
	
	@Bean 
	BagItemConfiguration bagItemConfiguration() {
		return new BagItemConfiguration();
	}
	
	@Bean 
	KieContainer kieConfiguration() {
		return bagItemConfiguration().kieContainer();
	}
	

}
