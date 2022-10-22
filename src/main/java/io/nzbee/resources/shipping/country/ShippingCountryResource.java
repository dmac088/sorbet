package io.nzbee.resources.shipping.country;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.shipping.country.ShippingCountryView;

public class ShippingCountryResource  extends RepresentationModel<ShippingCountryResource> {

	private final ShippingCountryView data;
	
	public ShippingCountryResource(ShippingCountryView product) {
		this.data = product;
	}
	
	public ShippingCountryView getData() {
		return data;
	}
	
}
