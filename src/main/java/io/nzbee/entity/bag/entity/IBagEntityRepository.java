package io.nzbee.entity.bag.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBagEntityRepository extends JpaRepository<BagEntity, Long> {
	
	@Query("SELECT be " +
		   "FROM BagEntity be " + 
		   "LEFT JOIN FETCH be.bagItems bie " + 
		   "JOIN be.party p " +
		   "JOIN p.partyUser u " + 
		   "WHERE u.username = :userName")
	Optional<BagEntity> findByPartyPartyUserUsername(String userName);

}

