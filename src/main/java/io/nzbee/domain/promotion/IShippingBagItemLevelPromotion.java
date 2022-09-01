package io.nzbee.domain.promotion;

import java.util.List;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;

public interface IShippingBagItemLevelPromotion {

	List<DiscountItem> execute(ShippingBagItem item);
	
}
