package io.nzbee.entity.product;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IProductService extends IEntityService<ProductEntity> {
	
	Optional<ProductEntity> findByCode(String productUPC);

	Optional<ProductEntity> findById(Long id);
	
	void save(String locale, String currency, ProductEntity product);

}
