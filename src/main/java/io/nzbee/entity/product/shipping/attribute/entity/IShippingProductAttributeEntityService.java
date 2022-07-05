package io.nzbee.entity.product.shipping.attribute.entity;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IShippingProductAttributeEntityService extends IEntityService<ShippingProductAttributeEntity> {

	Optional<ShippingProductAttributeEntity> findByCode(String locale, String code);
	
}