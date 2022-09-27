package io.nzbee.domain.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.promotion.bag.PromotionBag;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionServiceImpl implements IPromotionService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagPortService bagAdapter;
	
	@Override
	public void applyAll(IPromotionBagItem item) {
		// TODO Auto-generated method stub
	}
	
	public PromotionBag find(Locale locale, UserName userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters {}, {}", locale.getLocale().toLanguageTag(), userName);
		return bagAdapter.findByCode(locale, userName);
	}

	
}
