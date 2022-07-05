package io.nzbee.entity.inventory.type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryTypeRepository extends JpaRepository<InventoryType, Long>  {

	Optional<InventoryType> findByInventoryTypeCode(String code);
	
}
