package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.SearchController;

@Component
public class DiscoveryResourceAssembler  implements ISimpleResourceAssembler<DiscoveryResource> {

	
	public DiscoveryResourceAssembler() {
	}

	@Override
	public DiscoveryResource toModel(Map<String, ?> m) {
		//by passing in a map, we don't need to worry about a potentially unlimited number of parameters
		
		
		//discovery root object
		DiscoveryResource dr = new DiscoveryResource();
		
		//nouns / things / resources
		dr.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("cateogries").expand(m));
		dr.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer").expand(m));
		dr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag").expand(m));
		dr.add(Link.of(Constants.tokenUrl).withRel("token"));
		
		//verbs / actions
		dr.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("register").expand(m));
		dr.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirm").expand(m));
		dr.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("search").expand(m));
		dr.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("suggest").expand(m));
		
		return dr;
	}

}
