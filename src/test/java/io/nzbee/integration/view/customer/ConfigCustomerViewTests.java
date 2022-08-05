package io.nzbee.integration.view.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.Globals;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.AddressAdapter;
import io.nzbee.entity.adapters.view.CustomerViewAdapter;
import io.nzbee.entity.party.person.CustomerViewMapperImpl;
import io.nzbee.entity.party.person.ICustomerViewMapper;
import io.nzbee.integration.entity.bag.ConfigBagEntityTests;
import io.nzbee.integration.entity.party.address.ConfigPartyAddressEntityTests;
import io.nzbee.integration.view.beans.customer.CustomerViewBeanFactory;
import io.nzbee.integration.view.beans.customer.ICustomerViewBeanFactory;
import io.nzbee.integration.view.beans.customer.address.CustomerAddressViewBeanFactory;
import io.nzbee.integration.view.beans.customer.address.ICustomerAddressViewBeanFactory;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.view.customer.address.CustomerAddressDTOMapperImpl;
import io.nzbee.view.customer.address.ICustomerAddressDTOMapper;
import io.nzbee.view.ports.ICustomerAddressPortService;
import io.nzbee.view.ports.ICustomerPortService;

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
		 ConfigPartyAddressEntityTests.class,
		 ConfigBagEntityTests.class
		 })

public class ConfigCustomerViewTests {
	
	@Bean
	ICustomerViewBeanFactory customerViewBeanFactory() {
		return new CustomerViewBeanFactory();
	}
	
	@Bean 
	ICustomerAddressViewBeanFactory customerAddressViewBeanFactory() {
		return new CustomerAddressViewBeanFactory();
	}
	
	@Bean
	ICustomerAddressPortService customerAddressPortService() {
		return new AddressAdapter();
	}
	
	@Bean 
	ICustomerAddressDTOMapper customerAddressDTOMapper() {
		return new CustomerAddressDTOMapperImpl();
	}
	
	@Bean
	ICustomerPortService customerPortService() {
		return new CustomerViewAdapter();
	}
	
	@Bean 
	ICustomerViewMapper customerViewMapper() {
		return new CustomerViewMapperImpl();
	}
}
