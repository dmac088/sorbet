package io.nzbee.entity.party;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IEntityService;

public interface IPartyService extends IEntityService<Party> {

	Set<Party> findByRoleType(Class<?> roleType);
	
	Optional<Party> findByUsername(String userName);

	List<Party> findAll();

	Optional<Party> findById(Long id);
}
