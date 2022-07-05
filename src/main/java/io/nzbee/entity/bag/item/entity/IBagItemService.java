package io.nzbee.entity.bag.item.entity;

import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface IBagItemService extends IEntityService<BagItemEntity> {

	Optional<BagItemEntity> findById(Long id);

}
