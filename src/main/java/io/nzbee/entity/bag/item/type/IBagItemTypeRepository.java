package io.nzbee.entity.bag.item.type;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IBagItemTypeRepository extends JpaRepository<BagItemTypeEntity, Long>  {

	Optional<BagItemTypeEntity> findByBagItemTypeCode(String code);
	
}
