package io.nzbee.entity.bag.item.domain.regular;

import java.math.BigDecimal;
import java.util.Optional;

public interface IRegularBagItemDomainDTOService {

	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency);

	Optional<RegularBagItemDomainDTO> getNewShippingItem(String currency, String shipDest, String shipType,
			BigDecimal bagWeight);


}
