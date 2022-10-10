package io.nzbee.resources.product.shipping.destination;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.shipping.country.ShippingCountryView;

public class ShippingDestinationResource  extends RepresentationModel<ShippingDestinationResource> {

	private final ShippingCountryView data;
	
	public ShippingDestinationResource(final ShippingCountryView product) {
		this.data = product;
	}
	
	public ShippingCountryView getData() {
		return data;
	}
	
}
