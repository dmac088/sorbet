package io.nzbee.domain.ports;

import java.util.List;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.promotion.item.PromotionItem;

public interface IBagPortService  extends IDomainPortService<Bag> {

	Bag findByCode(String locale, String currency, String userName);

	List<PromotionItem> getPromotionItems(String locale, String currency, String userName);
	
	void save(Bag domainObject);
	
}
