package io.nzbee.domain.promotion;

import java.util.List;

import io.nzbee.domain.promotion.ports.IPromotionPort;

public interface IBagPromotion extends IPromotion {

	List<DiscountItem> execute(IPromotionPort object);
	
}
