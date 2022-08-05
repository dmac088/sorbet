package io.nzbee.integration.view.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.Globals;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.AddressAdapter;
import io.nzbee.integration.entity.party.address.ConfigPartyAddressEntityTests;
import io.nzbee.integration.view.beans.customer.address.CustomerAddressViewBeanFactory;
import io.nzbee.integration.view.beans.customer.address.ICustomerAddressViewBeanFactory;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.view.customer.address.CustomerAddressDTOMapperImpl;
import io.nzbee.view.customer.address.ICustomerAddressDTOMapper;
import io.nzbee.view.ports.ICustomerAddressPortService;

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
		 ConfigPartyAddressEntityTests.class
		 })

public class ConfigCustomerViewTests {
	
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
	
}
