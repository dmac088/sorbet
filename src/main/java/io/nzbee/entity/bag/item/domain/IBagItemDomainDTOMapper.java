package io.nzbee.entity.bag.item.domain;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTOMapper {

	RegularBagItem DTOToDo(Bag bag, BagItemWithQuantityDomainDTO dto);

	BagItemEntity doToEntity(RegularBagItem d);
	
	BagItemEntity doToEntity(ShippingBagItem d);

	RegularBagItem DTOToDo(Bag bag, BagItemDomainDTO biDto, int quantity);

	ShippingBagItem DTOToDo(Bag bag, BagItemDomainDTO biDto);

}
