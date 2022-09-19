package io.nzbee.view.product.shipping;

import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.IViewService;

public interface IShippingProductViewService extends IViewService<ShippingProductView> {

	ShippingProductView findByDestinationAndTypeAndBagWeight(Locale locale, String currency, String code, String type,
			BigDecimal totalWeight);

}
