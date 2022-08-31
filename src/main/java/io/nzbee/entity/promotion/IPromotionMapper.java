package io.nzbee.entity.promotion;

import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.promotion.bngn.PromotionBngnDTO;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;

public interface IPromotionMapper extends IDomainObjectMapper<Promotion, PromotionEntity, PromotionDomainDTO>  {

	Promotion DTOToDo(PromotionDiscDTO dto);

	Promotion DTOToDo(PromotionBngnDTO dto);


}
