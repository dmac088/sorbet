package io.nzbee.entity.role;

import java.util.Optional;

public interface IRoleTypeService {

	Optional<RoleTypeEntity> findByRoleTypeId(Long id);
	
	Optional<RoleTypeEntity> findByRoleTypeDesc(String desc);

}
