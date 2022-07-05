package io.nzbee.entity.party.address.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyAddressEntityServiceImpl implements IPartyAddressEntityService {

	@Autowired
	private IPartyAddressEntityRepository partyAddressRepository;
	
	@Override
	public Optional<PartyAddressEntity> findById(Long id) {
		return partyAddressRepository.findById(id);
	}

	@Override
	public void save(PartyAddressEntity t) {
		partyAddressRepository.save(t);
	}

	@Override
	public Optional<PartyAddressEntity> findByUsernameAndType(String userName, String addressTypeCode) {
		return partyAddressRepository.findByPartyPartyUserUsernameAndTypeAddressTypeCode(userName, addressTypeCode);
	}

}
