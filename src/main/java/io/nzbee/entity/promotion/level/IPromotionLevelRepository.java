package io.nzbee.entity.promotion.level;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionLevelRepository extends JpaRepository<PromotionLevelEntity, Long> {
		
	Optional<PromotionLevelEntity> findByPromotionLevelCode(String promotionTypeCode);
	
	List<PromotionLevelEntity> findAll();
}

