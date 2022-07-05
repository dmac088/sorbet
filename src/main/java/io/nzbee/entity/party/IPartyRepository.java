package io.nzbee.entity.party;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartyRepository extends JpaRepository<Party, Long> {

	List<Party> findAll();
	
	List<Party> findByPartyRolesRoleTypeRoleTypeDesc(String roleTypeDesc);
	
	Optional<Party> findByPartyUserUsername(String userName);

}
