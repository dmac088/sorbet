package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.controllers.CategoryResourceController;
import io.nzbee.resources.controllers.CustomerResourceController;
import io.nzbee.resources.controllers.SearchResourceController;

@Component
public class DiscoveryResourceAssembler  implements ISimpleResourceAssembler<DiscoveryResource> {

	public DiscoveryResourceAssembler() {
	}

	@Override
	public DiscoveryResource toModel() {
				
		//discovery root object
		DiscoveryResource dr = new DiscoveryResource();
		
		Link l0 = Link.of(Constants.tokenUrl).withRel("token");
		Link l1 = linkTo(methodOn(CategoryResourceController.class).getCategoryResource()).withRel("categoryResource");
		Link l2 = linkTo(methodOn(CustomerResourceController.class).getCustomerResource()).withRel("customerResource");
		Link l3 = linkTo(methodOn(SearchResourceController.class).getSearchURI()).withRel("searchResource");
		
		dr.add(l0);
		dr.add(l1);
		dr.add(l2);
		dr.add(l3);
	     
		return dr;
		
		
//		verbs / actions
//		dr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag").expand(m));
//		dr.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirm"));

		
		
	}

}
