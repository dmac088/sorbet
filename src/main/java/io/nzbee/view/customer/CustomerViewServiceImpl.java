package io.nzbee.view.customer;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;
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
	public void addItemToBag(String locale, Customer c, BagItem bagItem) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addItemToBag with parameters {}", c);
		customerService.addItemToBag(locale, c, bagItem);
	}
    

	@Override
	public void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".registerNewCustomer with parameters {}", customer);
		customerService.registerNewCustomer(customer, ipAddress, url, locale);
	}

}