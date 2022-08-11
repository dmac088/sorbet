package io.nzbee.resources.bag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.bag.BagView;

@Component
public class BagResourceAssembler extends RepresentationModelAssemblerSupport<BagView, BagResource> {

	
	public BagResourceAssembler() {
		super(BagController.class, BagResource.class);
	}

	public BagResource toModel(BagView bag, String locale, String currency) {
		BagResource br = this.toModel(bag);
		br.add(linkTo(methodOn(ProductController.class).getShippingProviders(locale, currency)).withRel("getShippingProviders"));
		br.add(linkTo(methodOn(ProductController.class).getShippingDestinations(locale)).withRel("getShippingDestinations"));
		br.add(linkTo(methodOn(ProductController.class).getByDestinationAndType(locale, currency, null, null, null)).withRel("getShippingProduct"));
		br.add(linkTo(methodOn(BagController.class).getCustomerBag(locale, currency, null)).withSelfRel());
		br.add(linkTo(methodOn(BagController.class).getBagContents(locale, currency, null)).withRel("bagContents"));
		br.add(linkTo(methodOn(BagController.class).addPhysicalItemToBag(locale, currency, null, null)).withRel("addItem"));
		br.add(linkTo(methodOn(BagController.class).addShippingItemToBag(locale, currency, null, null)).withRel("addShipping"));
		br.add(linkTo(methodOn(BagController.class).removeItemFromBag(locale, currency, null, null)).withRel("removeItem"));
		return br;
	}

	@Override
	public BagResource toModel(BagView entity) {
		return new BagResource(entity);
	}

}
