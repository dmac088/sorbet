package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTOService {

	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String locale);

	Optional<ShippingBagItemDomainDTO> getNewShippingItem(String locale, String shipDest, String shipType,
			BigDecimal bagWeight);

	Optional<ShippingBagItemDomainDTO> getShippingItem(String locale, String priceType, String code);

	void delete(BagItemEntity e);
	
}