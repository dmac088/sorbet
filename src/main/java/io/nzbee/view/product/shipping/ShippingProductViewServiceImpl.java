package io.nzbee.view.product.shipping;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.ports.IShippingProductPortService;

public class ShippingProductViewServiceImpl implements IShippingProductViewService {

	@Autowired
	private IShippingProductPortService shippingProductPortService;
	
	@Override
	public ShippingProductView findByDestinationAndTypeAndBagWeight(Locale locale, String code,
			String type, BigDecimal totalWeight) {
		return shippingProductPortService.findByDestinationAndTypeAndBagWeight(locale, code, type, totalWeight);
	}

}
 