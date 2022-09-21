package io.nzbee.entity.bag.domain.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import io.nzbee.entity.bag.entity.BagEntity;

public interface IPromotionBagDomainDTORepository extends JpaRepository<BagEntity, Long> {
	

}

