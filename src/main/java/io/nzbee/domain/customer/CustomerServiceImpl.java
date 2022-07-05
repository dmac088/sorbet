package io.nzbee.domain.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.ICustomerPortService;

public class CustomerServiceImpl implements ICustomerService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICustomerPortService customerService;

	@Override
	public Customer findByUsername(String locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		return customerService.findByUsername(locale, userName);
	}

	

}