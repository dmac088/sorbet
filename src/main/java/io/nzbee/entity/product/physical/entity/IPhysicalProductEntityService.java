package io.nzbee.entity.product.physical.entity;

import java.util.Optional;
import io.nzbee.entity.product.ProductEntity;

public interface IPhysicalProductEntityService {

	Optional<ProductEntity> findByCode(String productUPC);

	void save(ProductEntity product);

	void save(String locale, String currency, ProductEntity product);


}
