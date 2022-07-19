package io.nzbee.view.customer;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;

public interface ICustomerViewService {

	CustomerDTOOut findByUsername(String userName);
	
	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	void authWithoutPassword(String token);

	String validateVerificationToken(String token);

	void addItemToBag(String locale, Customer c, BagItem bagItem);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale);


	
}
