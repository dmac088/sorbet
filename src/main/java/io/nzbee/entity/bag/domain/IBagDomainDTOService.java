package io.nzbee.entity.bag.domain;

import java.util.Optional;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.entity.BagEntity;

@Service
public interface IBagDomainDTOService {
	
	void save(BagEntity b);

	Optional<BagDomainDTO> findByCode(String currency, String userName);

}
