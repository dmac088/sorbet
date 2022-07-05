package io.nzbee.entity.promotion.mechanic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionMechanicRepository extends JpaRepository<PromotionMechanicEntity, Long> {
		
	Optional<PromotionMechanicEntity> findByPromotionMechanicCode(String promotionMechanicCode);
	
	Optional<PromotionMechanicEntity> findByPromotionMechanicDesc(String promotionMechanicDesc);
	
	List<PromotionMechanicEntity> findAll();
}

