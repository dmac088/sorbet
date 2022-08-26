package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.promotion.Promotion;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	Bag applyAll(String locale, Bag bag); 
	
}
