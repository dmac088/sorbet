package io.nzbee.domain.ports;

import java.util.List;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.promotion.item.PromotionItem;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;

public interface IBagPortService  extends IDomainPortService<Bag> {

	List<PromotionItem> getPromotionItems(Locale locale, UserName userName);

	void save(Bag domainObject);

	Bag findByCode(Locale locale, String userName);
	
}
