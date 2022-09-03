package io.nzbee.domain.promotion.ports;

import java.math.BigDecimal;
import java.util.List;
import io.nzbee.domain.bag.item.IDiscountableBagItem;

public interface IBnGnFreePromotionPort {

	BigDecimal getTotalAmount();
	
	Long getTotalQuantity();

	List<IDiscountableBagItem> getDiscountableItems();
	
}
