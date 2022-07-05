package io.nzbee.view.customer;

import java.util.Locale;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;

public interface ICustomerViewService {

	CustomerDTOOut findByUsername(String userName);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, Locale locale);

	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	void authWithoutPassword(String token);

	String validateVerificationToken(String token);

	void addItemToBag(String locale, Customer c, BagItem bagItem);


	
}
