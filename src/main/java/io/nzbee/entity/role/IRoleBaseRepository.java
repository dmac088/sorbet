package io.nzbee.entity.role;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
public interface IRoleBaseRepository<R extends RoleEntity> extends JpaRepository<R, Long> {
	
	Optional<R> findByRoleId(Long Id);
	
}


