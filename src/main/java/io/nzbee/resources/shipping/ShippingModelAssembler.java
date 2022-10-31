package io.nzbee.resources.shipping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ShippingController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class ShippingModelAssembler implements ISimpleResourceAssembler<ShippingResource> {

	@Override
	public ShippingResource toModel() {
		ShippingResource cm =  new ShippingResource();
		Link l1 = linkTo(methodOn(ShippingController.class).getCountries(null, null)).withRel("countries");
		Link l2 = linkTo(methodOn(ShippingController.class).getShipCodes(null, null, null, null)).withRel("shipCodes");
		Link l3 = linkTo(methodOn(ShippingController.class).getHKPostageFee(null, null, null, null, null)).withRel("fee");
		
		cm.add(l1);
		cm.add(l2);
		cm.add(l3);
		
		return cm;
	}


}