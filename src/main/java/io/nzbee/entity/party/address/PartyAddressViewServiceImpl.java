package io.nzbee.entity.party.address;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyAddressViewServiceImpl implements IPartyAddressViewService {

	@Autowired
	private IPartyAddressViewRepository partyAddressRepository;
	
	@Override
	public Optional<PartyAddressViewDTO> findByUsernameAndRoleAndType(String userName, String roleName, String addressTypeCode) {
		return partyAddressRepository.findByUsernameAndRole(userName, roleName, addressTypeCode);
	}
	
	

}
