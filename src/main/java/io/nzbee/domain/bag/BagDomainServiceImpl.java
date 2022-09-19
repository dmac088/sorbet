package io.nzbee.domain.bag;

import javax.transaction.Transactional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItemDomainService;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.view.bag.item.BagItemViewIn;
import io.nzbee.view.product.shipping.ShippingItemDTOIn;

public class BagDomainServiceImpl implements IBagDomainService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagPortService bagService;
	
    @Autowired
    private IRegularBagItemDomainService domainBagItemService;
    
    @Autowired
    private IShippingBagItemDomainService shippingBagItemService;
	
    @Autowired
    @Qualifier("bagRulesContainer") 
    private KieContainer kieContainer;
	
	@Override
	public Bag findByCode(Locale locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}, {}", locale.getLocale(), locale.getCurrency().getCurrencyCode(), userName);
		return bagService.findByCode(locale, userName);
	}
	
	@Override
	public void addItemToBag(Locale locale, CouponCode coupon, String username) {
		Bag b = this.findByCode(locale, username);

		b.addCoupon(coupon);

		this.checkAllBagRules(b);

		this.save(b);
		
	}

	@Override
	@Transactional
	public void addShippingItem(Locale locale, ShippingItemDTOIn dto, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addShippingItem with parameters {}, {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), dto.getShippingProductCode(), username);
		
		//get the bag domain model with the items
    	Bag b = this.findByCode(locale, 
    							username);
		
    	//get the shipping item
		IShippingBagItem sbi = shippingBagItemService.getShippingItem(locale, b, dto.getShippingProductCode());
	
    	//add the shipping item to the bag
    	b.addShippingItem(sbi);
    	
    	//save the bag to the database 
    	this.save(b);
	}
	
	@Override
	@Transactional
	public void addPhysicalItem(Locale locale, BagItemViewIn dto, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addPhysicalItem with parameters {}, {}, {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), dto.getItemUPC(), dto.getItemQty(), username);
		
		//get the bag domain model with the items
    	Bag b = this.findByCode(locale,
    							username);

    	//check if the product already exists in the bag
    	boolean exists = b.bagItemExists(new ProductUPC(dto.getItemUPC()));
    	
    	//create a bag item if one does not exists otherwise retrieve the existing bag item
    	IRegularBagItem bagItem = exists 
    					? b.getBagItem(new ProductUPC(dto.getItemUPC())) 
    					: domainBagItemService.getNewPhysicalItem(locale, b, dto.getItemUPC(), dto.getItemQty());
    	
    	
    	b.addItem(bagItem, dto.getItemQty());
    	
    	//Run through the business rules
	    	//Checks out of stock
	    	//Checks bag and bagItem limits
	    	//Checks promotion eligibility, and applies discount 
    	
    	domainBagItemService.checkAllBagItemRules(bagItem);
    
     	if(bagItem.getBagItem().isErrors()) {
     		LOGGER.debug("The BagItem has errors!");
     		return;
    	}
    	
    	//save the current state of the bag to the database 
    	this.save(b);
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
