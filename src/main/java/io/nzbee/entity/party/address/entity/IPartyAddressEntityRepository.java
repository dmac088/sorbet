package io.nzbee.entity.party.address.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartyAddressEntityRepository extends JpaRepository<PartyAddressEntity, Long> {

	Optional<PartyAddressEntity> findByPartyPartyUserUsernameAndTypeAddressTypeCode(String userName, String addressTypeCode);
	
}
 