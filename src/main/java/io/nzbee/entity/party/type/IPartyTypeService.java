package io.nzbee.entity.party.type;

import java.util.Optional;

public interface IPartyTypeService {

	Optional<PartyTypeEntity> findByPartyTypeDesc(String partTypeDesc);
	
}
