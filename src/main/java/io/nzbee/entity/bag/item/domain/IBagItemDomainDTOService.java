package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;

import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTOService {

	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency);

	Optional<ShippingBagItemDomainDTO> getNewShippingItem(String currency, String shipDest, String shipType,
			BigDecimal bagWeight);

	Optional<ShippingBagItemDomainDTO> getShippingItem(String currency, String priceType, String code);

	void delete(BagItemEntity e);
	
}