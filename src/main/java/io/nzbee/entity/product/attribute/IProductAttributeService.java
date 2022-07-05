package io.nzbee.entity.product.attribute;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IProductAttributeService extends IEntityService<ProductAttributeEntity> {
	
	Optional<ProductAttributeEntity> findByCode(String locale, String code);

	Optional<ProductAttributeEntity> findById(Long id);
	
}
