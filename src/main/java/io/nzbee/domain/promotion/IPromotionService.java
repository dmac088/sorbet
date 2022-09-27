package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;

public interface IPromotionService  {

	void applyAll(IPromotionBagItem item);
	
}
