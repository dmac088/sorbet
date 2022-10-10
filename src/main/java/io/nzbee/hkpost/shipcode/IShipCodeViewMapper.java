package io.nzbee.hkpost.shipcode;

import java.util.List;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.code.ShippingCodeView;

public interface IShipCodeViewMapper {

	List<ShippingCodeView> toView(Locale locale, List<ShipCodeViewDTO> list);
	
}
