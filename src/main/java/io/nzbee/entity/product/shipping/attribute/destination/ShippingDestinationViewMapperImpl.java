package io.nzbee.entity.product.shipping.attribute.destination;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

@Component
public class ShippingDestinationViewMapperImpl implements IShippingDestinationViewMapper {

	@Override
	public ShippingDestinationView toView(ShippingDestinationDTO d) {
		ShippingDestinationView sdv = new ShippingDestinationView();
		sdv.setProductDestinationCode(d.getShippingDestinationCode());
		sdv.setProductDestinationDesc(d.getShippingDestinationDesc());
		return sdv;
	}

}
