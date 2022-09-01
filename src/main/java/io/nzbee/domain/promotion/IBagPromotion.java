package io.nzbee.domain.promotion;

import java.util.List;

public interface IBagPromotion<T> extends IPromotion {

	List<DiscountItem> execute(T object);
	
}
