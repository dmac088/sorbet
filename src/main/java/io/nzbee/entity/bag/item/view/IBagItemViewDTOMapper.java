package io.nzbee.entity.bag.item.view;

import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.view.bag.item.BagItemViewOut;

public interface IBagItemViewDTOMapper {

	BagItemViewOut DTOToView(BagItemViewDTO dto, IShippingBagItem shippingItem);

	BagItemViewOut DTOToView(BagItemViewDTO dto, IRegularBagItem rbi);

	
}