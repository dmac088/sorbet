package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.entity.IDomainObjectMapper;

public interface ICustomerDomainMapper extends IDomainObjectMapper<Customer, PersonEntity, PersonDomainDTO> {

	Customer EntityToDo(PersonEntity person);

	Customer toDomain(CustomerDTOIn customer);

	PersonEntity toEntity(CustomerDTOIn customer, String locale);

}
