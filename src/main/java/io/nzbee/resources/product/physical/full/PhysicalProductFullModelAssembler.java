package io.nzbee.resources.product.physical.full;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

@Component
public class PhysicalProductFullModelAssembler extends RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullModel> {
	
	public PhysicalProductFullModelAssembler() {
		super(ProductController.class, PhysicalProductFullModel.class);
	}

	@Override
	public PhysicalProductFullModel toModel(PhysicalProductFullView product) {
		PhysicalProductFullModel pr = new PhysicalProductFullModel(product);

		pr.add(linkTo(methodOn(ProductController.class).get(null, null,
				product.getProductUPC())).withSelfRel(),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(product.getProductUPC() + "_1.jpg"))
						.withRel("defaultImage"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(null))
						.withRel("images")
				);

		return pr;
	}

}
