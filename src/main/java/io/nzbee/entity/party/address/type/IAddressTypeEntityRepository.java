package io.nzbee.entity.party.address.type;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressTypeEntityRepository extends JpaRepository<AddressTypeEntity, Long> {

	Optional<AddressTypeEntity> findByAddressTypeCode(String addressTypeCode);
	
	Optional<AddressTypeEntity> findByAddressTypeDesc(String addressTypeDesc);

}
 