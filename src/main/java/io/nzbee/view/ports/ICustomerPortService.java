package io.nzbee.view.ports;

import io.nzbee.view.customer.CustomerDTOOut;

public interface ICustomerPortService {

	CustomerDTOOut findByUsername(String userName);

}
