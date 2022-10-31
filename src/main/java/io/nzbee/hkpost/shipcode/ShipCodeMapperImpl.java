package io.nzbee.hkpost.shipcode;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.code.ShippingCodeView;

@Component
public class ShipCodeMapperImpl implements IShipCodeViewMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<ShippingCodeView> toView(Locale locale, List<ShipCodeViewDTO> list) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView");
		return list.stream().map(dto -> {
			ShippingCodeView scv = new ShippingCodeView();
			scv.setCode(dto.getCode());
			scv.setName(dto.getName());
			return scv;
		}).collect(Collectors.toList());
		
	}
	
}
