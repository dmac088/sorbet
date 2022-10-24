package io.nzbee.view.shipping;

import java.math.BigDecimal;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.IViewService;

public interface IShippingFeeViewService extends IViewService<ShippingFeeView> {

	ShippingFeeView findByDestinationAndTypeAndBagWeight(Locale locale, String code, String type,
			BigDecimal totalWeight);

}
