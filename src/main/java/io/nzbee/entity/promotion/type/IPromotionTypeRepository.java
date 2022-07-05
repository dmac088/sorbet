package io.nzbee.entity.promotion.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionTypeRepository extends JpaRepository<PromotionTypeEntity, Long> {
		
	Optional<PromotionTypeEntity> findByPromotionTypeCode(String promotionTypeCode);
	
	List<PromotionTypeEntity> findAll();
}

