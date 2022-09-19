package io.nzbee.view.ports;
import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.product.shipping.ShippingProductView;

public interface IShippingProductPortService extends IViewPortService<ShippingProductView> {

	ShippingProductView findByDestinationAndTypeAndBagWeight(Locale locale, String currency, String code, String type,
			BigDecimal totalWeight);

}
