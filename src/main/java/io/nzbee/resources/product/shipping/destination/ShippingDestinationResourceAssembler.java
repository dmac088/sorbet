package io.nzbee.resources.product.shipping.destination;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

@Component
public class ShippingDestinationResourceAssembler extends RepresentationModelAssemblerSupport<ShippingDestinationView, ShippingDestinationResource> {
	
	public ShippingDestinationResourceAssembler() {
		super(ProductController.class, ShippingDestinationResource.class);
	}

	@Override
	public ShippingDestinationResource toModel(ShippingDestinationView destination) {
		ShippingDestinationResource sdr = new ShippingDestinationResource(destination);
		
		sdr.add(linkTo(methodOn(ProductController.class).getShippingTypes(null, destination.getProductDestinationCode(), null))
		.withRel("shippingTypes"));
		
		return sdr;
	}
	
}
