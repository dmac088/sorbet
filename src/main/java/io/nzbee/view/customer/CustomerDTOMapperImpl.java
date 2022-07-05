package io.nzbee.view.customer;

import org.springframework.stereotype.Component;
import io.nzbee.entity.party.person.PersonViewDTO;

@Component
public class CustomerDTOMapperImpl implements ICustomerDTOMapper {

	@Override
	public CustomerDTOOut toView(PersonViewDTO d) {
		CustomerDTOOut cdo = new CustomerDTOOut();
		cdo.setCustomerId(d.getCustomerNumber());
		cdo.setGivenName(d.getGivenName());
		cdo.setFamilyName(d.getFamilyName());
		return cdo;
	}


}
