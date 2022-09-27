package io.nzbee.domain.promotion.bag;

import io.nzbee.domain.valueObjects.Locale;

public interface IPromotionBagDomainService  {

	IPromotionBag findByCode(Locale locale, String userName);
	
	void save(IPromotionBag object);

	void update(IPromotionBag object);
	
	void checkAllBagRules(IPromotionBag bag);

}
