package io.nzbee.integration.entity.bag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.bag.entity.BagEntityServiceImpl;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.entity.BagItemEntityServiceImpl;
import io.nzbee.entity.bag.item.entity.IBagItemService;
import io.nzbee.entity.bag.status.BagItemStatusServiceImpl;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.bag.view.BagViewDTODaoPostgresImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.bag.view.IBagViewDTODao;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.integration.entity.beans.bag.BagEntityBeanFactory;
import io.nzbee.integration.entity.beans.bag.IBagEntityBeanFactory;
import io.nzbee.integration.entity.party.ConfigPartyEntityTests;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;

@Configuration
@Import({ConfigPartyEntityTests.class, 
	     ConfigProductEntityTests.class})
public class ConfigBagEntityTests {
	
	@Bean
	IBagEntityBeanFactory bagEntityBeanFactory() {
		return new BagEntityBeanFactory();
	}
	
	@Bean
	IBagItemService bagItemService() {
		return new BagItemEntityServiceImpl();
	}
	
	@Bean 
	IBagItemStatusService bagItemStatusService() {
		return new BagItemStatusServiceImpl();
	}
	
	@Bean
	IBagViewDTOService bagViewDTOService() {
		return new BagViewDTOServiceImpl();
	}
	
	@Bean 
	IBagViewDTODao bagViewDTODao() {
		return new BagViewDTODaoPostgresImpl();
	}
	
	@Bean
	IBagEntityService bagEntityService() {
		return new BagEntityServiceImpl();
	}
}
