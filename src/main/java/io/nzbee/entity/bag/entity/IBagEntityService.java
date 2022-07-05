package io.nzbee.entity.bag.entity;

import java.util.Optional;
import org.springframework.stereotype.Service;
import io.nzbee.entity.IEntityService;
@Service
public interface IBagEntityService extends IEntityService<BagEntity> {

	Optional<BagEntity> findByCode(String userName);

	Optional<BagEntity> findById(Long id);

}
