package io.nzbee.entity.party.address;

import java.util.Optional;

public interface IPartyAddressViewService {

	Optional<PartyAddressViewDTO> findByUsernameAndRoleAndType(String userName, String roleName, String addressTypeCode);
	
}
