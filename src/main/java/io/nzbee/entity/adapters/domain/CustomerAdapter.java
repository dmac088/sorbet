package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.exceptions.LocalizedException;
import io.nzbee.entity.party.person.ICustomerDomainMapper;

@Component
public class CustomerAdapter implements ICustomerPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private ICustomerDomainMapper customerDomainMapper;
	
	@Autowired
    private IBagEntityService bagService;
	
	@Autowired
    private IProductService productService;
	
	@Override
	public Customer findByUsername(String locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameters {}", userName);
		PersonEntity c = personService.findByUsernameAndRole(userName, Constants.partyRoleCustomer)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.customerNotFound, locale, userName));
		
		return customerDomainMapper.EntityToDo(c);
	}

	@Override
	public void addItemToBag(String locale, Customer c, BagItem bagItem) {
		
		//get the party of the bag, which will be a person
		PersonEntity t = personService.findByUsernameAndRole(c.getUserName(), Constants.partyRoleCustomer)
					.orElseThrow(() -> new LocalizedException(ErrorKeys.customerNotFound, locale));
		
		
		//get the product of the item the customer wishes to add
		String upc = bagItem.getProductUPC();
		ProductEntity p = productService.findByCode(upc)
					.orElseThrow(() -> new LocalizedException(ErrorKeys.productNotFound, locale));
		
		
		//get the bag of the person
		Optional<BagEntity> ob = bagService.findByCode(c.getUserName());
		
		BagEntity b = (ob.isPresent()) 
					  ? ob.orElseThrow(() -> new LocalizedException(ErrorKeys.bagNotFound, locale))
					  : new BagEntity();
		
		io.nzbee.entity.bag.item.entity.BagItemEntity bi = new io.nzbee.entity.bag.item.entity.BagItemEntity(p);
		bi.setQuantity(bagItem.getQuantity());
		b.addItem(bi);
		
		//set the bag / party relationship
		t.getPersonParty().setBag(b);
		b.setParty(t.getPersonParty());
				
		bagService.save(b);
	}

}
