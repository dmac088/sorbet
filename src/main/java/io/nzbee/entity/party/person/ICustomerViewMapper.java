package io.nzbee.entity.party.person;

import io.nzbee.view.customer.CustomerDTOOut;

public interface ICustomerViewMapper {

	CustomerDTOOut toView(PersonEntity person);

}
