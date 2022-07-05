package io.nzbee.entity.stock;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IStockOnHandService extends IEntityService<StockOnHandEntity> {

	Optional<StockOnHandEntity> findByProductCode(String productCode);

	Optional<StockOnHandEntity> findById(Long id);
	
}
