package io.nzbee.entity.bag.item.domain;

import java.util.Optional;

public interface IShippingBagItemDomainDTODao<T> {
	
	Optional<T> getItem(String locale, String currency, String upc);

	Optional<T> getNewItem(String locale, String currency, String priceType, String shipDest,
			String shipCode);

}
