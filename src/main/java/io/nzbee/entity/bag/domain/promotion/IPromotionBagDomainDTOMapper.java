package io.nzbee.entity.bag.domain.promotion;

import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.promotion.bag.PromotionBag;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.IEntityMapper;
import io.nzbee.entity.bag.entity.BagEntity;

public interface IPromotionBagDomainDTOMapper extends IDomainObjectMapper<PromotionBag, PromotionBagDomainDTO>, IEntityMapper<IPromotionBag, BagEntity> {

	PromotionBag toDo(PromotionBagDomainDTO dto);

}
