package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.bag.IPromotionBagItem;

public interface IPromotionService  {

	void applyAll(IPromotionBagItem item);
	
}
