package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;

public interface IShippingBagItemDomainDTODao<T> {
	
	Optional<T> getNewItem(String locale, String currency, String priceType, String shipDest,
			String shipType, BigDecimal bagWeight);

	Optional<T> getItem(String locale, String currency, String upc);

}
