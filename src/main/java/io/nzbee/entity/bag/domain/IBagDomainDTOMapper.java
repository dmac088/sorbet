package io.nzbee.entity.bag.domain;

import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.IEntityMapper;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.person.PersonDomainDTO;

public interface IBagDomainDTOMapper extends IDomainObjectMapper<Bag, BagDomainDTO>, IEntityMapper<Bag, BagEntity> {

	Bag toDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO bDto);

	Bag toDo(BagDomainDTO dto);

}
