package io.nzbee.entity.bag.item.domain;

import java.util.Optional;

public interface IRegularBagItemDomainDTODao<T> {

	Optional<T> getNewItem(String productUPC, String locale, String currency, String priceType);

}
