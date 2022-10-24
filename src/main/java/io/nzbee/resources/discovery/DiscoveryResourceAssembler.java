package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.controllers.CategoryResourceController;
import io.nzbee.resources.controllers.CustomerResourceController;
import io.nzbee.resources.controllers.ProductResourceController;
import io.nzbee.resources.controllers.SearchResourceController;
import io.nzbee.resources.controllers.ShippingResourceController;

@Component
public class DiscoveryResourceAssembler  implements ISimpleResourceAssembler<DiscoveryResource> {

	public DiscoveryResourceAssembler() {
	}

	@Override
	public DiscoveryResource toModel() {
				
		//discovery root object
		DiscoveryResource dr = new DiscoveryResource();
		
		Link l1 = linkTo(methodOn(CategoryResourceController.class).getCategoryResource()).withRel("categoryResource");
		Link l2 = linkTo(methodOn(CustomerResourceController.class).getCustomerResource()).withRel("customerResource");
		Link l3 = linkTo(methodOn(SearchResourceController.class).getSearchURI()).withRel("searchResource");
		Link l4 = linkTo(methodOn(ProductResourceController.class).getProductURI()).withRel("productResource");
		Link l5 = linkTo(methodOn(ShippingResourceController.class).getShippingResource()).withRel("shippingResource");
		
		
		
		dr.add(l1);
		dr.add(l2);
		dr.add(l3);
		dr.add(l4);
		dr.add(l5);
		
		return dr;		
		
	}

}
