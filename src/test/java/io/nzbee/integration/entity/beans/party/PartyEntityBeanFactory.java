package io.nzbee.integration.entity.beans.party;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.party.type.IPartyTypeRepository;
import io.nzbee.entity.party.type.PartyTypeEntity;
import io.nzbee.entity.role.IRoleTypeService;
import io.nzbee.entity.role.RoleEntity;
import io.nzbee.entity.role.RoleTypeEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.security.user.User;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;

@Service(value = "partyEntityBeanFactory")

public class PartyEntityBeanFactory implements IPartyEntityBeanFactory {

	@Autowired
	private IUserRoleService roleService;
	
	@Autowired 
	private IPartyTypeRepository partyTypeRespoitory;
	
	@Autowired 
	private IRoleTypeService roleTypeService;

	@Override
	public PersonEntity getBean() {
		UserRole ur = roleService.findByName("Customer");
		
		User user = new User();
		user.setEnabled(true);
		user.setUsername("mackdada");
		user.setPassword("mackdad1234");
		user.getUserRoles().add(ur);
		
		Party pty = new Party();
		Optional<PartyTypeEntity> ptype = partyTypeRespoitory.findById(new Long(1));
		pty.setPartyType(ptype.get());
				
		PersonEntity person = new PersonEntity();
		person.setPersonParty(pty);
		
		person.setFamilyName("Test Family Name");
		person.setGivenName("Test Given Name");

		RoleEntity re = new RoleEntity();
		CustomerEntity partyRole = new CustomerEntity();
		
		re.setRoleCustomer(partyRole);
		partyRole.setCustomerRole(re);
		
		Optional<RoleTypeEntity> rtype = roleTypeService.findByRoleTypeId(new Long(1));
		re.setRoleType(rtype.get());
		
		re.setRoleStart(new Date());
		partyRole.setCustomerNumber("9832145731");
		
		person.getPersonParty().addRole(re);
		re.setRoleParty(person.getPersonParty());
		
		user.setParty(person.getPersonParty());
		person.getPersonParty().addUser(user);
	
		return person;
	}
	
}
