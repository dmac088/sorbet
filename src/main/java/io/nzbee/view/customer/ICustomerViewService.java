package io.nzbee.view.customer;

public interface ICustomerViewService {

	CustomerDTOOut findByUsername(String userName);
	
}
