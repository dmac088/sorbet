package io.nzbee.domain.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.domain.ports.ICustomerPortService;

public class CustomerDomainServiceImpl implements ICustomerDomainService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICustomerPortService customerService;

	@Override
	public Customer findByUsername(String locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		return customerService.findByUsername(locale, userName);
	}

	@Override
	public void addCustomerLocation(CustomerDTOIn c, String clientIP) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addCustomerLocation with parameters {}", c);
		customerService.addCustomerLocation(c, clientIP);
	}

	@Override
	public String validateVerificationToken(String token) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".validateVerificationToken with parameters {}", token);
		return customerService.validateVerificationToken(token);
	}
	
	@Override
	public void authWithoutPassword(String token) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".authWithoutPassword with parameters {}", token);
		customerService.authWithoutPassword(token);
    }
    
	@Override
	public void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".registerNewCustomer with parameters {}", customer);
		customerService.registerNewCustomer(customer, ipAddress, url, locale);
	}


}