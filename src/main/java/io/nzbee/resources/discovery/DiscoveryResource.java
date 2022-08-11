package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.Constants;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.SearchController;

public class DiscoveryResource extends RepresentationModel<DiscoveryResource> {

	public DiscoveryResource(Map<String, ?> m) {
		
		//this one is a no brainer since categories drive the whole experience
		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("getAllCategories"));

		this.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("getCustomer"));
		
		this.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("registerCustomer").expand(m));
		this.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirmRegistration"));
		this.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("getBag").expand(m));
		
		
		//get product does not need to be here
		//this.add(linkTo(methodOn(ProductController.class).get(null, null, null)).withRel("getProduct"));
		
		
		//not sure where these go, think they can stay in the discovery object
		this.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("searchProduct").expand(m));
		this.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("searchSuggestion").expand(m));
		
		//the one is needed
		this.add(Link.of(Constants.tokenUrl).withRel("accessTokens"));

	}

}
