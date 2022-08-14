package io.nzbee.resources.product.physical.light;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class ProductURIResourceAssembler implements ISimpleResourceAssembler<ProductURIResource> {

	@Override
	public ProductURIResource toModel() {
		ProductURIResource pr = new ProductURIResource();
		
		Link l0 = linkTo(methodOn(ProductController.class).get(null, null, null)).withRel("product");
		
		pr.add(l0);
			
		return pr;
	}
    
}