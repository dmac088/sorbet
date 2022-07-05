package io.nzbee.entity.bag.domain;

import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.person.PersonDomainDTO;

public interface IBagDomainDTOMapper extends IDomainObjectMapper<Bag, BagEntity, BagDomainDTO> {

	Bag DTOToDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO bDto);

	Bag DTOToDo(BagDomainDTO dto);
	
}
