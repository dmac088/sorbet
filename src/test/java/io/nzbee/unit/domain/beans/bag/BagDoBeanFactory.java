package io.nzbee.unit.domain.beans.bag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;

@Service
public class BagDoBeanFactory implements IBagDoBeanFactory {
	
	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Override
	public Bag getBean() {
		Bag bag = new Bag(customerDoBeanFactory.getBean());
		
		return bag;
	}
	
}
