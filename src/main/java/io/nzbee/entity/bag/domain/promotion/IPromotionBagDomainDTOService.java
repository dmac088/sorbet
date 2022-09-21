package io.nzbee.entity.bag.domain.promotion;

import java.util.Optional;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.entity.BagEntity;

@Service
public interface IPromotionBagDomainDTOService {
	
	void save(BagEntity b);

	Optional<PromotionBagDomainDTO> findByCode(String locale, String currency, String userName);

}
