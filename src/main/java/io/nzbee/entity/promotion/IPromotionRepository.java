package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionRepository extends JpaRepository<PromotionEntity, Long> {
		
	Optional<PromotionEntity> findByPromotionCode(String promotionCode);
	
	Optional<PromotionEntity> findByAttributesLocaleAndAttributesPromotionDesc(String locale, String promotionDesc);
	
	Optional<PromotionEntity> findByAttributesPromotionDesc(String promotionDesc);
	
	List<PromotionEntity> findAll();
	
}

