package io.nzbee.entity.bag.entity;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IBagEntityService {

	Optional<BagEntity> findByCode(String userName);

	Optional<BagEntity> findById(Long id);

	void save(BagEntity t);

}
