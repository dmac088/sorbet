package io.nzbee.domain.bag;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.view.bag.item.BagItemViewIn;

public class BagDomainServiceImpl implements IBagDomainService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagPortService bagService;
	
    @Autowired
    private IRegularBagItemDomainService domainBagItemService;
	
    @Autowired
    @Qualifier("bagRulesContainer")
    private KieContainer kieContainer;
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}, {}", locale, currency, userName);
		return bagService.findByCode(locale, currency, userName);
	}
	
	@Override
	public Bag addPhysicalItem(String locale, String currency, BagItemViewIn dto, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addPhysicalItem with parameters {}, {}, {}, {}, {}", locale, currency, dto.getItemUPC(), dto.getItemQty(), username);
		
		//get the bag domain model with the items
    	Bag b = this.findByCode(locale, 
    							currency, 
    							username);

    	System.out.println("the number of items in the bag is " + b.getTotalItems());
    	System.out.println("the weight of items in the bag is " + b.getTotalWeight());
    	//check if the product already exists in the bag
    	boolean exists = b.bagItemExists(dto.getItemUPC());
    	
    	//create a bag item if one does not exists otherwise retrieve the existing bag item
    	RegularBagItem bagItem = exists 
    					? b.getBagItem(dto.getItemUPC()) 
    					: domainBagItemService.getNewPhysicalItem(locale, currency, b, dto.getItemUPC(), dto.getItemQty());
    		
    	if(exists) {
    		b.addItem(bagItem, dto.getItemQty());
    	}
    	
    	//Run through the business rules
	    	//Checks out of stock
	    	//Checks bag and bagItem limits
	    	//Checks promotion eligibility, and applies discount 
    	domainBagItemService.checkAllBagItemRules(bagItem);
    
     	if(bagItem.getBagItem().isErrors()) {
     		LOGGER.debug("The BagItem has errors!");
     		return b;
    		//return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toView(b)));
    	}
    	
    	if(!exists) {
    		b.addItem(bagItem, dto.getItemQty());
    	}
    	
    	//save the current state of the bag to the database 
    	this.save(b);
    	
    	return b;
	}
	
	
	@Override
	public Bag addShippingItem(String locale, String currency, BagItemViewIn dto, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addItem with parameters {}, {}, {}, {}, {}", locale, currency, dto.getItemUPC(), dto.getItemQty(), username);
		
		//get the bag domain model with the items
    	Bag b = this.findByCode(locale, 
    							currency, 
    							username);

    	
    	//check if the product already exists in the bag
    	boolean exists = b.bagItemExists(dto.getItemUPC());
    	
    	//create a bag item if one does not exists otherwise retrieve the existing bag item
    	RegularBagItem bagItem = exists 
    					? b.getBagItem(dto.getItemUPC()) 
    					: domainBagItemService.getNewPhysicalItem(locale, currency, b, dto.getItemUPC(), dto.getItemQty());
    		
    	if(exists) {
    		b.addItem(bagItem, dto.getItemQty());
    	}
    
     	if(bagItem.getBagItem().isErrors()) {
     		LOGGER.debug("The BagItem has errors!");
     		return b;
    		//return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toView(b)));
    	}
    	
    	if(!exists) {
    		b.addItem(bagItem, dto.getItemQty());
    	}
    	
    	//save the current state of the bag to the database 
    	this.save(b);
    	
    	return b;
	}

	@Override
	public void save(Bag object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		bagService.save(object);		
	}

	@Override
	public void update(Bag object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".update()");
		bagService.save(object);
	}

	@Override
	public void checkAllBagRules(Bag object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".checkAllBagRules()");
		KieSession kieSession = kieContainer.newKieSession();
    	kieSession.insert(object);
    	DroolsBagWrapper dpw = new DroolsBagWrapper(object);
    	kieSession.insert(dpw);
    	System.out.println("************* Fire Rules **************");
    	kieSession.fireAllRules();
        System.out.println("************************************");
        System.out.println("Customer bag\n" + object.getCustomer().getUserName());
	}

}
