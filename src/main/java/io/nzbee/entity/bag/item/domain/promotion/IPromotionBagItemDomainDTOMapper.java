package io.nzbee.entity.bag.item.domain.promotion;

import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.promotion.bag.item.PromotionBagItem;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IPromotionBagItemDomainDTOMapper {

	PromotionBagItem toDo(IPromotionBag bag, PromotionBagItemDomainDTO dto);
	
	BagItemEntity toEntity(PromotionBagItem d);
	
}
