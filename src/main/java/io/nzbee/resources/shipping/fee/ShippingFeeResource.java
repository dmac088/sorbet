package io.nzbee.resources.shipping.fee;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.shipping.country.ShippingCountryView;

public class ShippingFeeResource  extends RepresentationModel<ShippingFeeResource> {

	private final ShippingCountryView data;
	
	public ShippingFeeResource(ShippingCountryView product) {
		this.data = product;
	}
	
	public ShippingCountryView getData() {
		return data;
	}
	
}
