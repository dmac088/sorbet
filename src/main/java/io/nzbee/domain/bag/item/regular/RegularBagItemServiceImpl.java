package io.nzbee.domain.bag.item.regular;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.DroolsBagItemWrapper;
import io.nzbee.domain.ports.IBagItemPortService;

public class RegularBagItemServiceImpl implements IRegularBagItemDomainService{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagItemPortService bagItemService;
	
    @Autowired
    @Qualifier("bagItemRulesContainer")
    private KieContainer kieContainer;
	
	@Override
	public RegularBagItem getNewPhysicalItem(String locale, String currency, Bag bag, String itemUPC, Long quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewPhysicalItem with parameters {}, {}", itemUPC, quantity);
		return bagItemService.getNewPhysicalItem(locale, currency, bag, itemUPC, quantity);
	}

	@Override 
	public void save(RegularBagItem object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		bagItemService.save(object);
	}

	@Override
	public void delete(RegularBagItem object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete()");
		bagItemService.delete(object);
	}

	@Override
	public void checkAllBagItemRules(RegularBagItem object) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".checkAllBagItemRules()");
		KieSession kieSession = kieContainer.newKieSession();
    	kieSession.insert(object);
    	DroolsBagItemWrapper dpw = new DroolsBagItemWrapper(object);
    	kieSession.insert(dpw);
    	//System.out.println(StringUtils.join(dpw.getPromotionCodes()));
    	System.out.println("************* Fire Bag Item Rules **************");
    	kieSession.fireAllRules();
        System.out.println("************************************");
        System.out.println("Customer bag\n" + object.getBagItem().getBag().getCustomer().getUserName());
	}

}
