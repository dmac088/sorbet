package io.nzbee.entity.bag.view;

import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.bag.BagView;

public interface IBagViewDTOMapper {

	BagView toView(BagViewDTO bDto);

	BagView toView(BagViewDTO bagViewDTO, BigDecimal totalPostage, Locale locale);
	
}
