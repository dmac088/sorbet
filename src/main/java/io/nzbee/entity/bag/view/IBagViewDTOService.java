package io.nzbee.entity.bag.view;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IBagViewDTOService {
	
	BigDecimal getBagWeight(String userName);

	Optional<BagViewDTO> findByCode(String locale, String currency, String userName);


}
