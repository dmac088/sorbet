package io.nzbee.view.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.ICustomerPortService;

public class CustomerViewServiceImpl implements ICustomerViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private ICustomerPortService customerService;
	
	@Override
	public CustomerDTOOut findByUsername(String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		return customerService.findByUsername(userName);
	}

	
}