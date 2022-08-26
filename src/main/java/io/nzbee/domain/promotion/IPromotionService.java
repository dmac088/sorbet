package io.nzbee.domain.promotion;

import io.nzbee.domain.bag.Bag;

public interface IPromotionService  {

	Promotion findAll(Bag b);

}
