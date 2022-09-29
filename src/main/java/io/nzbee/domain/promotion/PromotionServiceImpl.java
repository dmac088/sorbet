package io.nzbee.domain.promotion;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.promotion.bag.DroolsPromotionBagItemWrapper;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionServiceImpl implements IPromotionService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    @Qualifier("promotionRulesContainer") 
    private KieContainer kieContainer;
	
	@Autowired
	private IBagPortService bagAdapter;
	
	@Override
	public void applyAll(IPromotionBag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".checkAllBagRules()");
		KieSession kieSession = kieContainer.newKieSession();
		bag.getBagItems().stream().forEach(bi -> {
			DroolsPromotionBagItemWrapper dpw = new DroolsPromotionBagItemWrapper(bi);
	    	kieSession.insert(dpw);
	    	System.out.println("************* Fire Rules **************");
	    	kieSession.fireAllRules();
	        System.out.println("************************************");
		});	
	}
	
	@Override
	public IPromotionBag find(Locale locale, UserName userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters {}, {}", locale.getLocale().toLanguageTag(), userName);
		return bagAdapter.findPromotionBagByCode(locale, userName);
	}

	@Override
	public void save(IPromotionBag pb) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		bagAdapter.save(pb);
	}
	
}
