package io.nzbee.resources.product.physical.light;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class ProductNavigationURIResourceAssembler implements ISimpleResourceAssembler<ProductNavigationURIResource> {

	@Override
	public ProductNavigationURIResource toModel() {
		ProductNavigationURIResource pr = new ProductNavigationURIResource();
	
		Link l0 = linkTo(methodOn(ProductController.class).getProducts(null, null, null, null, null, null, null)).withRel("products");
		
		pr.add(l0);
		return pr;
	}
    
}