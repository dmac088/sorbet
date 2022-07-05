package io.nzbee.security.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface IUserRepository extends JpaRepository<User, Long> {

	
    @Query("SELECT DISTINCT user " +
    	   "FROM User user " +
           "LEFT JOIN FETCH user.roles AS roles " +
           "WHERE user.username = :username")
    User loadUserByUsername(@Param("username") String username);
    
    
}