package io.nzbee.entity.bag.item.type;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IBagItemTypeService extends IEntityService<BagItemTypeEntity> {

	Optional<BagItemTypeEntity> findByCode(String shippingbagitemtype);

}