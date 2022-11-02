package io.nzbee.entity.bag.domain;

import java.math.BigDecimal;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.entity.bag.entity.BagEntity;

public interface IBagDomainDTOMapper  {

	//Bag toDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO bDto);

	Bag toDo(BagDomainDTO dto, BigDecimal price);
	
	BagEntity toEntity(IPromotionBag pb);

	BagEntity toEntity(IBag d);

		

}
