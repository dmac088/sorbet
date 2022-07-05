package io.nzbee.integration.entity.party;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.role.IRoleTypeService;
import io.nzbee.entity.role.RoleTypeServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.party.IPartyEntityBeanFactory;
import io.nzbee.integration.entity.beans.party.PartyEntityBeanFactory;
import io.nzbee.security.Encoders;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.user.UserService;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRoleServiceImpl;

@Configuration
@Import({ConfigEntityTests.class, Encoders.class, SecurityBeanConfiguration.class})
public class ConfigPartyEntityTests {
	
	@Bean
	public IPersonService personService() {
		return new PersonServiceImpl();
	}
	
	@Bean
	public IPartyEntityBeanFactory partyEntityBeanFactory() {
		return new PartyEntityBeanFactory();
	}
	
	@Bean
	public IUserRoleService userRoleService() {
		return new UserRoleServiceImpl();
	}
	
	@Bean
	public IRoleTypeService roleTypeService() {
		return new RoleTypeServiceImpl();
	}
	
	@Bean 
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
}
