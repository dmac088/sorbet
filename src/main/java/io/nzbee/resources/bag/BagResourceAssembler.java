package io.nzbee.resources.bag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.BagController;
import io.nzbee.view.bag.BagView;

@Component
public class BagResourceAssembler extends RepresentationModelAssemblerSupport<BagView, BagResource> {

	public BagResourceAssembler() {
		super(BagController.class, BagResource.class);
	}

	@Override
	public BagResource toModel(BagView bag) {
		BagResource br = new BagResource(bag);
		br.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withSelfRel());
		br.add(linkTo(methodOn(BagController.class).getBagContents(null, null, null)).withRel("bagContents"));
		br.add(linkTo(methodOn(BagController.class).addPhysicalItemToBag(null, null, null, null)).withRel("addItem"));
		br.add(linkTo(methodOn(BagController.class).addShippingItemToBag(null, null, null, null)).withRel("addShipping"));
		br.add(linkTo(methodOn(BagController.class).removeItemFromBag(null, null, null, null)).withRel("removeItem"));
		return br;
	}

}
