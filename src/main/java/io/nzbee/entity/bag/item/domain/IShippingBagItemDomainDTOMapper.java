package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IShippingBagItemDomainDTOMapper {

	ShippingBagItem toDo(IBag bag, ShippingBagItemDomainDTO dto, Long quantity, BigDecimal price, Locale locale);

	BagItemEntity toEntity(IShippingBagItem d);

	ShippingBagItem toDo(IBag bag, ShippingBagItemDomainDTO dto, BigDecimal price, Locale locale);

}
