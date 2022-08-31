package io.nzbee.domain.ports;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;

public interface ICustomerPortService {

	Customer findByUsername(String locale, String userName);

	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	String validateVerificationToken(String token);

	void authWithoutPassword(String token);

	void save(CustomerDTOIn customer, String locale);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale);

}
