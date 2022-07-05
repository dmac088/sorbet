package io.nzbee.integration.view.beans.bag;


import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;

@Service
public class BagViewBeanFactory implements IBagViewBeanFactory {
	
	@Override
	public Bag getBean(Customer c) {
	
		Bag bag = new Bag(c);
		
		return bag;
		
	}

	@Override
	public Bag getBean() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
