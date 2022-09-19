package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.IDomainObjectMapper;

public interface ICustomerDomainMapper extends IDomainObjectMapper<Customer, PersonDomainDTO> {

	Customer toDo(PersonEntity person);

	Customer toDo(CustomerDTOIn customer);

	PersonEntity toEntity(CustomerDTOIn dtoObject, Locale locale);

}
