package io.nzbee.domain.promotion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.promotion.bag.IPromotionItem;
import io.nzbee.domain.promotion.bag.PromotionItem;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionServiceImpl implements IPromotionService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagPortService bagAdapter;
	
	@Override
	public void applyAll(IPromotionItem item) {
		// TODO Auto-generated method stub
	}
	
//	public List<PromotionBag find(Locale locale, UserName userName) {
//		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters {}, {}", locale.getLocale(), userName);
//		return bagAdapter.findByCode(locale, userName);
//	}

	
}
