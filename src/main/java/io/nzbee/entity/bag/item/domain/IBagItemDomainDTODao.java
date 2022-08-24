package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;

public interface IBagItemDomainDTODao {

	Optional<BagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency, String priceType);
	
	Optional<BagItemDomainDTO> getNewShippingItem(String currency, String priceType, String shipDest, String shipType, BigDecimal bagWeight);
	
	Optional<BagItemDomainDTO> getShippingItem(String currency, String priceType, String code);
	
}
