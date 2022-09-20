package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTOService {

	Optional<ShippingBagItemDomainDTO> getNewShippingItem(String locale, String currency, String shipDest, String shipType,
			BigDecimal bagWeight);

	Optional<ShippingBagItemDomainDTO> getShippingItem(String locale, String currency, String priceType, String code);

	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String locale, String currency, String productUPC);
	
	void delete(BagItemEntity e);
	
}