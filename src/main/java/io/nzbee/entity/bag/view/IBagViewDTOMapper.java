package io.nzbee.entity.bag.view;

import io.nzbee.view.bag.BagView;

public interface IBagViewDTOMapper {

	BagView DTOToView(BagViewDTO bagViewDTO);
	
}
