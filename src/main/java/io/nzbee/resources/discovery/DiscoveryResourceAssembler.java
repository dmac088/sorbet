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
		DiscoveryResource dr = new DiscoveryResource();
		
		//this one is a no brainer since categories drive the whole experience
		dr.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("getAllCategories"));

		dr.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("getCustomer"));
		
		dr.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("registerCustomer").expand(m));
		dr.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirmRegistration"));
		dr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("getBag").expand(m));
		
		
		//get product does not need to be here
		//dr.add(linkTo(methodOn(ProductController.class).get(null, null, null)).withRel("getProduct"));
		
		
		//not sure where these go, think they can stay in the discovery object
		dr.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("searchProduct").expand(m));
		dr.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("searchSuggestion").expand(m));
		
		dr.add(Link.of(Constants.tokenUrl).withRel("accessTokens"));
		
		return dr;
	}

}
