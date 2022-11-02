package io.nzbee.entity.bag.item.view;

import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.bag.item.BagItemViewOut;

public interface IBagItemViewDTOMapper {

	BagItemViewOut toView(BagItemViewDTO dto);

	BagItemViewOut toView(BagItemViewDTO bi, BigDecimal totalPostage, Locale locale);
	
}