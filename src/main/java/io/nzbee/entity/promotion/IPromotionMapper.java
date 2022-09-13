package io.nzbee.entity.promotion;

import io.nzbee.domain.promotion.Promotion;

public interface IPromotionMapper {

	Promotion DTOToDo(PromotionDomainDTO dto);

}
