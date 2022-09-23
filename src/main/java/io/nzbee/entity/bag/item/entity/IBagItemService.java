package io.nzbee.entity.bag.item.entity;

import java.util.Optional;
public interface IBagItemService {

	Optional<BagItemEntity> findById(Long id);

}
