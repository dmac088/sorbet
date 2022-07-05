package io.nzbee.entity.party.person;

import io.nzbee.Constants;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.role.customer.CustomerEntity;
import org.springframework.stereotype.Component;

@Component(value="customerMapper")
public class CustomerMapperImpl implements ICustomerDomainMapper {

	@Override
	public Customer DTOToDo(PersonDomainDTO dto) {

		Customer co = new Customer(
			dto.getUserName(),
			dto.getCustomerNumber(),
			dto.getEnabled()
		);		
		return co;	
	}

	@Override
	public PersonEntity doToEntity(Customer d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer EntityToDo(PersonEntity person) {
		Customer co = new Customer(
						person.getPersonParty().getUser().getUsername(),
						((CustomerEntity) person.getPersonParty().getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Constants.partyRoleCustomer)).findAny().get().getRoleCustomer()).getCustomerNumber(),
						person.getPersonParty().getUser().isEnabled()
					);
		return co;
	}
}
