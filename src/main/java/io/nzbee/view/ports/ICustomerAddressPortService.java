package io.nzbee.view.ports;

import io.nzbee.view.customer.address.CustomerAddressDTOIn;
import io.nzbee.view.customer.address.CustomerAddressDTOOut;

public interface ICustomerAddressPortService {
	
	CustomerAddressDTOOut findByUsernameAndType(String userName, String addressTypeCode);

	void save(CustomerAddressDTOIn addressObject, String userName);

}
