package io.nzbee.resources.product.shipping.destination;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.ProductController;
import io.nzbee.view.product.shipping.country.ShippingCountryView;

@Component
public class ShippingDestinationResourceAssembler extends RepresentationModelAssemblerSupport<ShippingCountryView, ShippingDestinationResource> {
	
	public ShippingDestinationResourceAssembler() {
		super(ProductController.class, ShippingDestinationResource.class);
	}

	@Override
	public ShippingDestinationResource toModel(ShippingCountryView destination) {
		ShippingDestinationResource sdr = new ShippingDestinationResource(destination);
		
		sdr.add(linkTo(methodOn(ProductController.class).getShippingTypes(null, destination.getProductDestinationCode(), null))
		.withRel("shippingTypes"));
		
		return sdr;
	}
	
}
