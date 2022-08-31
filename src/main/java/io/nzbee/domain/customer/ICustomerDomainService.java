package io.nzbee.domain.customer;

import io.nzbee.domain.customer.dto.in.CustomerDTOIn;

public interface ICustomerDomainService {

	Customer findByUsername(String locale, String userName);
	
	void addCustomerLocation(CustomerDTOIn c, String clientIP);

	void authWithoutPassword(String token);

	String validateVerificationToken(String token);

	void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale);

}
