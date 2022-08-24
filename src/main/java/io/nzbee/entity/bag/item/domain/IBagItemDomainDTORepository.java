package io.nzbee.entity.bag.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTORepository extends JpaRepository<BagItemEntity, String> {

	
}