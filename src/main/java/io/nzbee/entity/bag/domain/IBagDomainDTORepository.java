package io.nzbee.entity.bag.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import io.nzbee.entity.bag.entity.BagEntity;

public interface IBagDomainDTORepository extends JpaRepository<BagEntity, Long> {
	

}

