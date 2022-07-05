package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;

public interface ICustomerViewMapper {

	CustomerDTOOut toView(PersonEntity person);
	
	PersonEntity toEntity(CustomerDTOIn dtoObject);

	Customer toDomain(CustomerDTOIn customer);

}
