package io.nzbee.domain.promotion;

import java.util.List;
import io.nzbee.domain.bag.item.regular.RegularBagItem;

public interface IRegularBagItemLevelPromotion {

	List<DiscountItem> execute(RegularBagItem item);
	
}
