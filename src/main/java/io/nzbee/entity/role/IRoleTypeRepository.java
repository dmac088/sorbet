package io.nzbee.entity.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleTypeRepository extends JpaRepository<RoleTypeEntity, Long>  {
	
	public Optional<RoleTypeEntity> findByRoleTypeId(Long id);
	
	public Optional<RoleTypeEntity> findByRoleTypeDesc(String desc);

}
