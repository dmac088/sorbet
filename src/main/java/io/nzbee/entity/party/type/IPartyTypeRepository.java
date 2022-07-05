package io.nzbee.entity.party.type;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartyTypeRepository extends JpaRepository<PartyTypeEntity, Long> {

	List<PartyTypeEntity> findAll();

	Optional<PartyTypeEntity> findByPartyTypeDesc(String desc);
}
