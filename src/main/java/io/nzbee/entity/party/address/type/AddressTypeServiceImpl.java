package io.nzbee.entity.party.address.type;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressTypeServiceImpl implements IAddressTypeService {

	@Autowired
	private IAddressTypeEntityRepository addressTypeRepository;
	
	@Override
	public Optional<AddressTypeEntity> findById(long id) {
		return addressTypeRepository.findById(id);
	}

	@Override
	public Optional<AddressTypeEntity> findByCode(String code) {
		return addressTypeRepository.findByAddressTypeCode(code);
	}

}
