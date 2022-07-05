package io.nzbee.entity.bag.view;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.nzbee.entity.bag.entity.BagEntity;

public interface IBagViewDTORepository extends JpaRepository<BagEntity, Long> {

	
	@Query("SELECT sum(pphy.weightDimension * bie.quantity) as weight " +
		   "FROM BagEntity be " + 
		   "JOIN be.bagItems bie " + 
		   "JOIN bie.product prd " + 
		   "JOIN prd.productPhysical pphy " +
		   "JOIN be.party p " +
		   "JOIN p.partyUser u " + 
		   "WHERE u.username = :userName")
	BigDecimal getBagWeight(String userName);
	
}

