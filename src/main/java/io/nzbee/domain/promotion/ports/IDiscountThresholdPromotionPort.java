package io.nzbee.domain.promotion.ports;

import java.util.List;
import io.nzbee.domain.bag.item.IDiscountableBagItem;

public interface IDiscountThresholdPromotionPort {
	
	//we need to spread the discount amount across the line items
	List<IDiscountableBagItem> getDiscountableItems();
	
	
}
