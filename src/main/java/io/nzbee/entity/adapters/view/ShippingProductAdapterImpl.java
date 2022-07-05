package io.nzbee.entity.adapters.view;

import java.math.BigDecimal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.product.shipping.view.IShippingProductViewDTOService;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.product.shipping.view.IShippingProductViewDTOMapper;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.shipping.ShippingProductView;

public class ShippingProductAdapterImpl  implements IShippingProductPortService {

	@Autowired
	private IShippingProductViewDTOService shippingProductService;
	
	@Autowired
	private IShippingProductViewDTOMapper productMapper;

	@Override
	@Transactional
	public ShippingProductView findByDestinationAndTypeAndBagWeight(String locale, String currency, String code,
			String type, BigDecimal totalWeight) {
		return productMapper.toView(shippingProductService.findByDestinationAndTypeAndBagWeight(locale, currency, code, type, totalWeight)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, code + " - " + type + " - " + totalWeight.toString())));
	}

}
