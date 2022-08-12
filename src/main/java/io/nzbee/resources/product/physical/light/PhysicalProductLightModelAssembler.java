package io.nzbee.resources.product.physical.light;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.resources.ILocalizedResourceAssember;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Component
public class PhysicalProductLightModelAssembler implements ILocalizedResourceAssember<PhysicalProductLightModel, PhysicalProductLightView> {


	@Override
	public PhysicalProductLightModel toModel(PhysicalProductLightView p, String locale, String currency) {
		PhysicalProductLightModel pr = new PhysicalProductLightModel(p);

		pr.add(linkTo(methodOn(ProductController.class).get(locale, currency, p.getProductUPC())).withRel("fullProduct"),

			   linkTo(methodOn(ProductController.class).getImageWithMediaType(p.getProductUPC() + "_1.jpg"))
						.withRel("defaultImage"),
						
			   linkTo(methodOn(ProductController.class).getImageWithMediaType(null))
						.withRel("images")
				);

		return pr;
	}
}
