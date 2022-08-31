package io.nzbee.entity.adapters.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.entity.party.person.ICustomerViewMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.view.customer.CustomerDTOOut;
import io.nzbee.view.ports.ICustomerPortService;

public class CustomerViewAdapter implements ICustomerPortService {

private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ICustomerViewMapper customerViewMapper;
	
	@Autowired
	private IPersonService personService;
	
	@Override
	public CustomerDTOOut findByUsername(String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		
		return customerViewMapper.toView(personService.findByUsernameAndRole(userName, Constants.partyRoleCustomer).get());
	}


}
