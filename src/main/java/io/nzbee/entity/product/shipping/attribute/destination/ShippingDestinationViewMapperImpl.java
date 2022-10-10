package io.nzbee.entity.product.shipping.attribute.destination;

import org.springframework.stereotype.Component;

import io.nzbee.view.shipping.country.ShippingCountryView;

@Component
public class ShippingDestinationViewMapperImpl implements IShippingDestinationViewMapper {

	@Override
	public ShippingCountryView toView(ShippingDestinationDTO d) {
		ShippingCountryView sdv = new ShippingCountryView();
		sdv.setCountryCode(d.getShippingDestinationCode());
		sdv.setCountryDesc(d.getShippingDestinationDesc());
		return sdv;
	}

}
