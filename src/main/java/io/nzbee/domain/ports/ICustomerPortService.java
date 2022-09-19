package io.nzbee.domain.ports;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.domain.valueObjects.Locale;

public interface ICustomerPortService {

	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	String validateVerificationToken(String token);

	void authWithoutPassword(String token);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, Locale locale);

	Customer findByUsername(Locale locale, String userName);

	void save(CustomerDTOIn customer, Locale locale);

}
