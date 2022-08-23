package io.nzbee.entity.bag.item.view;

import io.nzbee.view.bag.item.BagItemViewOut;

public interface IBagItemViewDTOMapper {

	BagItemViewOut DTOToView(BagItemViewDTO dto);
	
}