package io.nzbee.entity.bag.status;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IBagItemStatusService extends IEntityService<BagItemStatus>  {

	Optional<BagItemStatus> findByCode(String code);

	Optional<BagItemStatus> findById(Long id);

}
