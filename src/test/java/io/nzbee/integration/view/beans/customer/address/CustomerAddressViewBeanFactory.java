package io.nzbee.integration.view.beans.customer.address;


import org.springframework.stereotype.Service;
import io.nzbee.view.customer.address.CustomerAddressDTOIn;

@Service

public class CustomerAddressViewBeanFactory implements ICustomerAddressViewBeanFactory {

	@Override
	public CustomerAddressDTOIn getBean() {
		
		CustomerAddressDTOIn a = new CustomerAddressDTOIn();
		a.setAddressLine1("Test Line 1");
		a.setAddressLine2("Test Line 2");
		a.setAddressLine3("Test Line 3");
		a.setCountry("Test Country");
		a.setAddressTypeCode("BIL01");
		a.setPostCode("Test PC");
		return a;
	}
	
}
