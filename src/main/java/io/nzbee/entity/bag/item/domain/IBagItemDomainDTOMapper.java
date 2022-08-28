package io.nzbee.entity.bag.item.domain;

import io.nzbee.domain.bag.Bag;

public interface IBagItemDomainDTOMapper<T, E, D> {

	D DTOToDo(Bag bag, T dto);

	D DTOToDo(Bag bag, T dto, Long quantity);
	
	E doToEntity(D d);
	

}
