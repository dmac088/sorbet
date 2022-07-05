package io.nzbee.entity.adapters.view;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.party.person.ICustomerViewMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.exceptions.AlreadyExistsException;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.exceptions.PasswordsNotMatchException;
import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.IUserService;
import io.nzbee.security.user.User;
import io.nzbee.security.user.verificationtoken.VerificationToken;
import io.nzbee.security.user.verificationtoken.VerificationTokenRepository;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;
import io.nzbee.view.ports.ICustomerPortService;

public class CustomerViewAdapter implements ICustomerPortService {

private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ICustomerViewMapper customerViewMapper;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private IBagEntityService bagService;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	@Override
	public CustomerDTOOut findByUsername(String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		
		return customerViewMapper.toView(personService.findByUsernameAndRole(userName, Constants.partyRoleCustomer).get());
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
	public void addItemToBag(String locale, Customer customer, BagItem bagItem) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addItemToBag with parameters {}", customer);
		
		//get the party of the bag, which will be a person
		PersonEntity t = personService.findByUsernameAndRole(customer.getUserName(), Constants.partyRoleCustomer)
					.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.customerNotFound, locale,customer.getUserName()));
		
		
		//get the product of the item the customer wishes to add
		String upc = bagItem.getProductUPC();
		ProductEntity p = productService.findByCode(upc)
					.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, upc));
		
		
		//get the bag of the person
		Optional<BagEntity> ob = bagService.findByCode(customer.getUserName());
		
		BagEntity b = (ob.isPresent()) 
				? ob.get()
				: new BagEntity();
		
		io.nzbee.entity.bag.item.entity.BagItemEntity bi = new io.nzbee.entity.bag.item.entity.BagItemEntity(p);
		bi.setQuantity(bagItem.getQuantity());
		b.addItem(bi);
		
		//set the bag / party relationship
		t.getPersonParty().setBag(b);
		b.setParty(t.getPersonParty());
				
		bagService.save(b);
	}
    
	@Override
    @Transactional
	public void registerNewCustomer(CustomerDTOIn customer, String ipAddress, String url, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".registerNewCustomer with parameters {}", customer);
		boolean exists = personService.userExists(customer.getUserName(), Constants.partyRoleCustomer);
		if(exists) {
			throw new AlreadyExistsException(Constants.partyRoleCustomer + " with username " + customer.getUserName() + " already exists!");
		}
					
		//create a domain object for which to process the rules we defined 
		Customer c = customerViewMapper.toDomain(customer);
		
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
  		this.save(customer);
			
	    this.addCustomerLocation(customer, ipAddress);

	}

	@Override
	@Transactional
	public void save(CustomerDTOIn customer) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save with parameters {}", customer);
		personService.save(customerViewMapper.toEntity(customer));
	}

}
