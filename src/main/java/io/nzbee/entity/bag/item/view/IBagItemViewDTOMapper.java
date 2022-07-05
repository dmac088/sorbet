package io.nzbee.entity.bag.item.view;

import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.view.bag.item.BagItemViewOut;

public interface IBagItemViewDTOMapper {

	BagItemViewOut DTOToView(BagViewDTO bDo, BagItemViewDTO dto);
	
}