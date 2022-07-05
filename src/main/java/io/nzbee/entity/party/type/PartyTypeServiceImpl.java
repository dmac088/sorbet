package io.nzbee.entity.party.type;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyTypeServiceImpl implements IPartyTypeService {

	@Autowired
	private IPartyTypeRepository partyTypeRepository;
	
	@Override
	public Optional<PartyTypeEntity> findByPartyTypeDesc(String partyTypeDesc) {
		return partyTypeRepository.findByPartyTypeDesc(partyTypeDesc);
	}
	
}
