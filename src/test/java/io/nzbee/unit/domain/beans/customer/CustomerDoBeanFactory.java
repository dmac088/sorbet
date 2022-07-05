package io.nzbee.unit.domain.beans.customer;


import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.Customer;

@Service

public class CustomerDoBeanFactory implements ICustomerDoBeanFactory {

	@Override
	public final Customer getBean() {
		
		Customer c = 
				new Customer(
				"tst088",
				"123456789",
				true
				);
		c.setPassword("1234", "1234");
			
		return c;
	}
	
}
