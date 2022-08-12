package io.nzbee.resources.product.physical.light;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.Collections;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class ProductURIResourceAssembler implements ISimpleResourceAssembler<ProductURIResource, Map<String, String>> {

	@Override
	public ProductURIResource toModel(Map<String, String> m) {
		ProductURIResource pr = new ProductURIResource();
		
		Boolean hasNulls = (Collections.frequency(m.values(), null) > 0);
		
		Link l0 = linkTo(methodOn(ProductController.class).getProducts(null, null, null, null, null, null, null)).withRel("products");
		
		if(hasNulls) {
			pr.add(l0);
			return pr;
		}
		
		pr.add(l0.expand(m));
		return pr;
	}
    
}