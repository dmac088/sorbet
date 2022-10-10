package io.nzbee.view.shipping;

import java.math.BigDecimal;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.IViewService;

public interface IShippingProductViewService extends IViewService<ShippingProductView> {

	ShippingProductView findByDestinationAndTypeAndBagWeight(Locale locale, String code, String type,
			BigDecimal totalWeight);

}
