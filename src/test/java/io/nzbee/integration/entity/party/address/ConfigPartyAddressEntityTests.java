package io.nzbee.integration.entity.party.address;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.party.address.IPartyAddressViewService;
import io.nzbee.entity.party.address.PartyAddressViewServiceImpl;
import io.nzbee.entity.party.address.entity.IPartyAddressEntityService;
import io.nzbee.entity.party.address.entity.PartyAddressEntityServiceImpl;
import io.nzbee.entity.party.address.type.AddressTypeServiceImpl;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.integration.entity.beans.party.address.IPartyAddressEntityBeanFactory;
import io.nzbee.integration.entity.beans.party.address.PartyAddressEntityBeanFactory;
import io.nzbee.integration.entity.party.ConfigPartyEntityTests;

@Configuration
@Import({ConfigPartyEntityTests.class})
public class ConfigPartyAddressEntityTests {
	
	@Bean
	IPartyAddressViewService partyAddressService() {
		return new PartyAddressViewServiceImpl();
	}
	
	@Bean
	IPartyAddressEntityBeanFactory partyAddressEntityBeanFactory() {
		return new PartyAddressEntityBeanFactory();
	}
	
	@Bean 
	IAddressTypeService addressTypeService() {
		return new AddressTypeServiceImpl();
	}

	@Bean 
	IPartyAddressEntityService partyAddressEntityService() {
		return new PartyAddressEntityServiceImpl();
	}
}
