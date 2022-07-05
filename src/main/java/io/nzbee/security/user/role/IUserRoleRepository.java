package io.nzbee.security.user.role;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String roleName);
    
    Optional<UserRole> findById(Long id);
    
}