package io.nzbee.domain.customer;


public interface ICustomerService {

	Customer findByUsername(String locale, String userName);
	
}
