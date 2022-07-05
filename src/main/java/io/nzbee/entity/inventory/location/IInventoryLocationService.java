package io.nzbee.entity.inventory.location;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IInventoryLocationService extends IEntityService<InventoryLocation> {

	Optional<InventoryLocation> findByCode(String locationCode);

	Optional<InventoryLocation> findById(Long id);
	
}
