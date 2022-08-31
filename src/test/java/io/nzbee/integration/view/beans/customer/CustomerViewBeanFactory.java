package io.nzbee.integration.view.beans.customer;


import org.springframework.stereotype.Service;

import io.nzbee.domain.customer.dto.in.CustomerDTOIn;

@Service
public class CustomerViewBeanFactory implements ICustomerViewBeanFactory {

	@Override
	public CustomerDTOIn getBean() {
		
		CustomerDTOIn c = new CustomerDTOIn();
		
		c.setUserName("tst088");
		c.setCustomerId("123456789");
		c.setPassword("1234");
		c.setEnabled(true);
		c.setGivenName("Bob");
		c.setFamilyName("Jones");
			
		return c;
	}
	
}
