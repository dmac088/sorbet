package io.nzbee.entity.bag.item.domain;

import io.nzbee.domain.bag.IBag;

public interface IBagItemDomainDTOMapper<T, E, D> {

	D toDo(IBag bag, T dto);

	D toDo(IBag bag, T dto, Long quantity);
	
	E toEntity(D d);

}
