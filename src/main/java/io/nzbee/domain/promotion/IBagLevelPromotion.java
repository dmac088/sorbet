package io.nzbee.domain.promotion;

import java.util.List;
import io.nzbee.domain.bag.Bag;

public interface IBagLevelPromotion {

	List<DiscountItem> execute(Bag bag);
	
}
