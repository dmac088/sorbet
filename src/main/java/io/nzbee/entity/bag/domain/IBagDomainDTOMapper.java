package io.nzbee.entity.bag.domain;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.IEntityMapper;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.person.PersonDomainDTO;

public interface IBagDomainDTOMapper extends IDomainObjectMapper<IBag, BagDomainDTO>, IEntityMapper<IBag, BagEntity> {

	Bag toDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO bDto);

	Bag toDo(BagDomainDTO dto);

	BagEntity toEntity(IPromotionBag pb);

}
