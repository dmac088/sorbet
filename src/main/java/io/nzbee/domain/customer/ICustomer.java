package io.nzbee.domain.customer;

import io.nzbee.domain.valueObjects.UserName;

public interface ICustomer {

	String getCustomerID();

	UserName getUserName();

}
