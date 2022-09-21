package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.promotion.bag.PromotionBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public interface IBagPortService  extends IDomainPortService<Bag> {

	void save(Bag domainObject);

	Bag findByCode(Locale locale, String userName);

	PromotionBag findByCode(Locale locale, UserName userName);
	
}
