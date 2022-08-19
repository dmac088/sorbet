package io.nzbee.integration.view.bag;
import org.springframework.context.annotation.Bean;
import io.nzbee.integration.entity.bag.ConfigBagEntityTests;
import io.nzbee.integration.view.beans.bag.BagViewBeanFactory;
import io.nzbee.integration.view.beans.bag.IBagViewBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.Globals;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.domain.BagDomainAdapter;
import io.nzbee.entity.adapters.domain.CustomerAdapter;
import io.nzbee.entity.adapters.view.BagViewAdapterImpl;
import io.nzbee.entity.bag.domain.BagDomainDTODaoImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOMapperImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOServiceImpl;
import io.nzbee.entity.bag.domain.IBagDomainDTODao;
import io.nzbee.entity.bag.domain.IBagDomainDTOMapper;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.BagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.view.BagItemViewDTOMapperImpl;
import io.nzbee.entity.bag.item.view.IBagItemViewDTOMapper;
import io.nzbee.entity.bag.view.BagViewDTODaoPostgresImpl;
import io.nzbee.entity.bag.view.BagViewDTOMapperImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.bag.view.IBagViewDTODao;
import io.nzbee.entity.bag.view.IBagViewDTOMapper;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.ICustomerDomainMapper;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOMapper;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOMapperImpl;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.view.ports.IBagPortService;

@Configuration
//we need to import other configuration classes to create the spring context 
//we cannot arbitrarily create beans in this config class since the configuration classes are 
//not simply services or components but actual configurations, also we don't want to component scan
//io.nzbee.* since this will load things (services, components, configs) we don't want into the spring context
@Import({DataSourceBeanMochi.class,
		 DataSourceBeanSecurity.class,
		 FileStorageProperties.class,
		 FileStorageServiceUpload.class,
		 Globals.class,
		 ConfigBagEntityTests.class
		 })

public class ConfigBagViewTests {
	
	@Bean
	IBagPortService bagPortService() {
		return new BagViewAdapterImpl(); 
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
	IBagViewDTOMapper bagViewDTOMapper() {
		return new BagViewDTOMapperImpl();
	}
	
	@Bean
	IBagItemViewDTOMapper bagItemViewDTOMapper() {
		return new BagItemViewDTOMapperImpl();
	}
	
	@Bean
	IPhysicalProductDTOMapper physicalProductDTOMapper() {
		return new PhysicalProductDTOMapperImpl();
	}
	
	@Bean
	io.nzbee.domain.ports.IBagPortService bagDomainPortService() {
		return new BagDomainAdapter(); 
	}
	
	@Bean
	IBagDomainDTOService bagDomainDTOService() {
		return new BagDomainDTOServiceImpl();
	}
	
	@Bean
	IBagDomainDTODao bagDomainDTODao() { 
		return new BagDomainDTODaoImpl();
	}
	
	@Bean
	IBagDomainDTOMapper bagDomainDTOMapper() {
		return new BagDomainDTOMapperImpl();
	}
	
	@Bean
	ICustomerDomainMapper customerDomainMapper() {
		return new CustomerMapperImpl();
	}
	
	@Bean
	IBagItemDomainDTOMapper regularBagItemDomainDTOMapper() {
		return new BagItemDomainDTOMapperImpl();
	}
	
	@Bean 
	ICustomerService customerService() {
		return new CustomerServiceImpl();
	}
	
	@Bean
	ICustomerPortService customerAdapter() {
		return new CustomerAdapter();
	}
	
	@Bean
	IBagViewBeanFactory bagViewBeanFactory() {
		return new BagViewBeanFactory();
	}
}
