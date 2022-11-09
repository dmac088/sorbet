package io.nzbee.entity.bag.item.domain;

import java.util.Optional;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTOService {

	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String locale, String currency, String productUPC);
	
	void delete(BagItemEntity e);

	Optional<ShippingBagItemDomainDTO> getShippingItem(String locale, String currency, String code);

	Optional<ShippingBagItemDomainDTO> getNewShippingItem(String locale, String currency, String shipDest,
			String shipType);
	
}