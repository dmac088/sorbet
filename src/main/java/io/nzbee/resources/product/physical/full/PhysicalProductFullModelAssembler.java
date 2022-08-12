package io.nzbee.resources.product.physical.full;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.ProductController;
import io.nzbee.resources.ILocalizedResourceAssember;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

@Component
public class PhysicalProductFullModelAssembler implements ILocalizedResourceAssember<PhysicalProductFullModel, PhysicalProductFullView> {
	

	@Override
	public PhysicalProductFullModel toModel(PhysicalProductFullView p, String locale, String currency) {
		PhysicalProductFullModel pr = new PhysicalProductFullModel(p);

		pr.add(linkTo(methodOn(ProductController.class).get(locale, currency, p.getProductUPC())).withSelfRel(),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(p.getProductUPC() + "_1.jpg")).withRel("defaultImage"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(null)).withRel("images"));

		return pr;
	}

}
