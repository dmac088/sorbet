package io.nzbee.entity.role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

	List<RoleEntity> getAllRoles();

	Optional<RoleEntity> getRole(Long id);
	
}
