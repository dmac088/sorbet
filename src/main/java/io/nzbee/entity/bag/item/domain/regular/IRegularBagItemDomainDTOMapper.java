package io.nzbee.entity.bag.item.domain.regular;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IRegularBagItemDomainDTOMapper {

	RegularBagItem DTOToDo(Bag bag, RegularBagItemWithQuantityDomainDTO dto);

	BagItemEntity doToEntity(RegularBagItem d);

	RegularBagItem DTOToDo(Bag bag, RegularBagItemDomainDTO biDto, int quantity);

	
}