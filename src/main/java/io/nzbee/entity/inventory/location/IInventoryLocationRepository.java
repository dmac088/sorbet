package io.nzbee.entity.inventory.location;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryLocationRepository extends JpaRepository<InventoryLocation, Long> {
	
	Optional<InventoryLocation> findByLocationCode(String locationCode);
	
}

