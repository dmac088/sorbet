package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.item.IPromotionItem;

public interface IPromotionService  {

	void applyAll(IPromotionItem item);
	
}
