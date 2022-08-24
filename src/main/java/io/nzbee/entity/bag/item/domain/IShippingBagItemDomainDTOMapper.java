package io.nzbee.entity.bag.item.domain;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.entity.bag.domain.BagDomainItemDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IShippingBagItemDomainDTOMapper {

	ShippingBagItem DTOToDo(Bag bag, BagDomainItemDTO biDto);

	BagItemEntity doToEntity(ShippingBagItem d);

}
