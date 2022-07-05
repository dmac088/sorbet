package io.nzbee.entity.inventory.type;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IInventoryTypeService extends IEntityService<InventoryType> {

	Optional<InventoryType> findByCode(String code);

}
