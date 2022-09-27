package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public interface IPromotionService  {

	void applyAll(IPromotionBag pb);

	IPromotionBag find(Locale locale, UserName userName);
	
}
