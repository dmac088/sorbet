package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public interface IBagPortService  extends IDomainPortService<Bag> {

	IBag findBagByCode(Locale locale, UserName userName);
	
	IPromotionBag findPromotionBagByCode(Locale locale, UserName userName);

	void save(IBag object);
	
}
