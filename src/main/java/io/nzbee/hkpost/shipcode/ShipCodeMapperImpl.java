package io.nzbee.hkpost.shipcode;

import java.util.List;
import org.springframework.stereotype.Component;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.code.ShippingCodeView;

@Component
public class ShipCodeMapperImpl implements IShipCodeViewMapper {

	@Override
	public List<ShippingCodeView> toView(Locale locale, List<ShipCodeViewDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
