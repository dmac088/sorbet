package io.nzbee.entity.bag.view;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IBagViewDTOService {

	Optional<BagViewDTO> findByCode(String locale, String currency, String rootCategory, String userName);

	BigDecimal getBagWeight(String userName);


}
