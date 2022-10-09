package io.nzbee.entity.product.shipping.attribute.destination;

import org.springframework.stereotype.Component;

import io.nzbee.view.product.shipping.country.ShippingCountryView;

@Component
public class ShippingDestinationViewMapperImpl implements IShippingDestinationViewMapper {

	@Override
	public ShippingCountryView toView(ShippingDestinationDTO d) {
		ShippingCountryView sdv = new ShippingCountryView();
		sdv.setProductDestinationCode(d.getShippingDestinationCode());
		sdv.setProductDestinationDesc(d.getShippingDestinationDesc());
		return sdv;
	}

}
