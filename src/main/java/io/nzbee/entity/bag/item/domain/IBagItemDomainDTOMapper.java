package io.nzbee.entity.bag.item.domain;

import io.nzbee.domain.bag.Bag;

public interface IBagItemDomainDTOMapper<T, E, D> {

	D toDo(Bag bag, T dto);

	D toDo(Bag bag, T dto, Long quantity);
	
	E toEntity(D d);

}
