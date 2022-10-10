package io.nzbee.view.ports;
import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingProductView;

public interface IShippingProductPortService extends IViewPortService<ShippingProductView> {

	ShippingProductView findByDestinationAndTypeAndBagWeight(Locale locale, String code, String type,
			BigDecimal totalWeight);


}
