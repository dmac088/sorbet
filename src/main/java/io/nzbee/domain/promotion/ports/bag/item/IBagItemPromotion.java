package io.nzbee.domain.promotion.ports.bag.item;

import java.util.List;

import io.nzbee.domain.promotion.DiscountItem;

public interface IBagItemPromotion<F>  {

	List<DiscountItem> execute(F object);
	
}
