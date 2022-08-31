package io.nzbee.entity.promotion.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPromotionTypeRepository extends JpaRepository<PromotionTypeEntity, Long> {
		
	Optional<PromotionTypeEntity> findByPromotionTypeCode(String promotionTypeCode);
	
	
	
	@Query("SELECT distinct pt " +
		   "FROM PromotionEntity pe " + 
		   "JOIN pe.promotionType pt " + 
		   "WHERE pe.triggerCode = :triggerCode")
	Optional<PromotionTypeEntity> findByPromotionEntityPromotionTriggerCode(String triggerCode);
	
	List<PromotionTypeEntity> findAll();
}

