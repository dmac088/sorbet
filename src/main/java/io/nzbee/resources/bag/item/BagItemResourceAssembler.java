package io.nzbee.resources.bag.item;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.BagController;
import io.nzbee.controllers.ProductController;
import io.nzbee.view.bag.item.BagItemViewOut;

@Component
public class BagItemResourceAssembler extends RepresentationModelAssemblerSupport<BagItemViewOut, BagItemResource> {

	public BagItemResourceAssembler() {
		super(BagController.class, BagItemResource.class);
	}


	@Override
	public BagItemResource toModel(BagItemViewOut bag) {
		BagItemResource br = new BagItemResource(bag);
		br.add(linkTo(methodOn(BagController.class).getBagContents(null, null, null)).withSelfRel());
		br.add(linkTo(methodOn(ProductController.class).getImageWithMediaType(bag.getItemUPC() + "_1.jpg")).withRel("defaultImage"));
		
		//check if the item is a shipping item and if it is then 
		//add a link to add to
		
		return br;
	}

}
