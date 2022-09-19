package io.nzbee.domain.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.nzbee.domain.promotion.item.IPromotionItem;
import io.nzbee.domain.valueObjects.UserName;

public class PromotionServiceImpl implements IPromotionService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public void applyAll(IPromotionItem item) {
		// TODO Auto-generated method stub
		
	}
	
	public IPromotionItem find(UserName userName) {
		return null;
	}

	
}
