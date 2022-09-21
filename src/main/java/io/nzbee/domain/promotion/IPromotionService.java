package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.bag.IPromotionItem;

public interface IPromotionService  {

	void applyAll(IPromotionItem item);
	
}
