package io.nzbee.entity.party.address.entity;

import java.util.Optional;

public interface IPartyAddressEntityService {

	Optional<PartyAddressEntity> findByUsernameAndType(String userName, String addressTypeCode);

	Optional<PartyAddressEntity> findById(Long id);

	void save(PartyAddressEntity pa);
	
}
