package io.nzbee.entity.party.person;

import io.nzbee.Constants;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.view.customer.CustomerDTOOut;

public class CustomerViewMapperImpl implements ICustomerViewMapper {

	@Override
	public CustomerDTOOut toView(PersonEntity person) {
		CustomerDTOOut c = new CustomerDTOOut();
		c.setCustomerId(((CustomerEntity) person.getPersonParty().getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Constants.partyRoleCustomer)).findAny().get().getRoleCustomer()).getCustomerNumber());
		c.setGivenName(person.getGivenName());
		c.setFamilyName(person.getFamilyName());
		return c;
	}
	

}
