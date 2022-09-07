package io.nzbee.domain.promotion.ports;

import java.util.List;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.value.Money;

public interface IBnGnFreePromotionPort {

	Money getTotalAmount();
	
	Long getTotalQuantity();

	List<IDiscountableBagItem> getDiscountableItems();
	
	
}
