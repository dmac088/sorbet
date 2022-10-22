package io.nzbee.resources.shipping.country;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.view.shipping.country.ShippingCountryView;

@Component
public class ShippingCountryResourceAssembler extends RepresentationModelAssemblerSupport<ShippingCountryView, ShippingCountryResource> {
	
	public ShippingCountryResourceAssembler() {
		super(ProductController.class, ShippingCountryResource.class);
	}

	@Override
	public ShippingCountryResource toModel(ShippingCountryView destination) {
		ShippingCountryResource sdr = new ShippingCountryResource(destination);
		
		return sdr;
	}
	
}
