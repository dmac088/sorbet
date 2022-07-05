package io.nzbee.entity.role;

import java.util.Optional;

import io.nzbee.entity.role.customer.CustomerEntity;

public interface IRoleRepository extends IRoleBaseRepository<RoleEntity>  {
	
	Optional<RoleEntity> findByRoleId(Long id);
	
	Optional<CustomerEntity> findByRolePartyPartyUserUsername(String userName);

}
