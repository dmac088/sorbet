package io.nzbee.entity.bag.item.view;

import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.view.bag.item.BagItemViewOut;

public interface IBagItemViewDTOMapper {

	BagItemViewOut DTOToView(BagItemViewDTO dto, RegularBagItem rbi);

	BagItemViewOut DTOToView(BagItemViewDTO dto, ShippingBagItem shippingItem);
	
}