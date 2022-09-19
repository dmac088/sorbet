package io.nzbee.entity.adapters.domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.exceptions.AlreadyExistsException;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.exceptions.PasswordsNotMatchException;
import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.IUserService;
import io.nzbee.security.user.User;
import io.nzbee.security.user.verificationtoken.VerificationToken;
import io.nzbee.security.user.verificationtoken.VerificationTokenRepository;
import io.nzbee.entity.party.person.ICustomerDomainMapper;

@Component
public class CustomerAdapter implements ICustomerPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private ICustomerDomainMapper customerDomainMapper;
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	@Override
	public Customer findByUsername(String locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		PersonEntity c = personService.findByUsernameAndRole(userName, Constants.partyRoleCustomer)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.customerNotFound, locale, userName));
		
		return customerDomainMapper.toDo(c);
	}


	@Override
	@Transactional
	public void addCustomerLocation(CustomerDTOIn c, String clientIP) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addCustomerLocation with parameters {}", c);
		
		UserDetails u = userService.findUserByUsername(c.getUserName());
		userService.addUserLocation((User) u, clientIP);
	}

	@Override
	@Transactional
	public String validateVerificationToken(String token) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".validateVerificationToken with parameters {}", token);
		
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return Constants.TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return Constants.TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
        userService.saveRegisteredUser(user);
        return Constants.TOKEN_VALID;
	}
	
	@Override
	@Transactional
	public void authWithoutPassword(String token) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".authWithoutPassword with parameters {}", token);
		
		User user = userService.getUser(token);

        List<Authority> authorities = user.getUserRoles()
                .stream()
                .map(r -> r.getAuthorities())
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    
	@Override
    @Transactional
	public void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".registerNewCustomer with parameters {}", customer);
		boolean exists = personService.userExists(customer.getUserName(), Constants.partyRoleCustomer);
		if(exists) {
			throw new AlreadyExistsException(Constants.partyRoleCustomer + " with username " + customer.getUserName() + " already exists!");
		}
					
		//create a domain object for which to process the rules we defined 
		Customer c = customerDomainMapper.toDo(customer);
		
		//set the password on the domain object
		c.setPassword(customer.getPassword(), customer.getConfirmPassword());
		
		//check that the domain object contains errors
		if(c.isError()) {
			System.out.println("Customer errors exist!");
			if(c.getErrors().containsKey("PWDMTCH")) {
				throw new PasswordsNotMatchException(c.getErrors().get("PWDMTCH"));
			}
		}

		//if we succeed then we persist the CustomerDTOIn, not the domain object
  		this.save(customer, locale);
			
	    this.addCustomerLocation(customer, ipAddress);

	}

	@Override
	@Transactional
	public void save(CustomerDTOIn customer, String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save with parameters {}", customer);
		personService.save(customerDomainMapper.toEntity(customer, locale));
	}
}
