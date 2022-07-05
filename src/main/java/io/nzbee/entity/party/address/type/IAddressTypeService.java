package io.nzbee.entity.party.address.type;

import java.util.Optional;



public interface IAddressTypeService {

	Optional<AddressTypeEntity> findById(long id);

	Optional<AddressTypeEntity> findByCode(String code);
	
	
}
