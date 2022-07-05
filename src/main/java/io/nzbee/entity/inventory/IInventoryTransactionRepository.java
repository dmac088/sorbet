package io.nzbee.entity.inventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
	
	List<InventoryTransaction> findByProductProductUPC(String productCode);
	
}

