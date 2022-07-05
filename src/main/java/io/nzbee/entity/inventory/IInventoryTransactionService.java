package io.nzbee.entity.inventory;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IInventoryTransactionService extends IEntityService<InventoryTransaction> {

	List<InventoryTransaction> findByProductCode(String productCode);

	Optional<InventoryTransaction> findById(Long id);

	

}
