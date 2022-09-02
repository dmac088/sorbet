package io.nzbee.domain.promotion.ports;

import java.util.List;
import io.nzbee.domain.bag.item.IBagItem;

public interface IPctgDiscountPromotionPort {

	//we need to spread the discount amount across the line items
	List<IBagItem> getItems();
	
}
