package io.nzbee.entity.stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockOnHandRepository extends JpaRepository<StockOnHandEntity, Long> {
	
	Optional<StockOnHandEntity> findByProductPhysicalProductProductUPC(String productUPC);
	
}

