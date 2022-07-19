package io.nzbee.view.ports;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;

public interface ICustomerPortService {

	CustomerDTOOut findByUsername(String userName);

	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	String validateVerificationToken(String token);

	void authWithoutPassword(String token);

	void addItemToBag(String locale, Customer customer, BagItem bagItem);

	void save(CustomerDTOIn customer, String locale);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale);

	
}
