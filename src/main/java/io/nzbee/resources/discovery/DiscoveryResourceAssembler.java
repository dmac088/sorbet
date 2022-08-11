package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;

@Component
public class DiscoveryResourceAssembler  implements ISimpleResourceAssembler<DiscoveryResource> {

	
	@Autowired 
	private Globals globals;
	
	public DiscoveryResourceAssembler() {
	}

	@Override
	public DiscoveryResource toModel(LocaliseDTO l) {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("locale", l.getLocale());
		m.put("currency", l.getCurrency());
		m.put("category", globals.getDefaultProductRootCategoryCode());
		
		//discovery root object
		DiscoveryResource dr = new DiscoveryResource();
		
		//nouns / things / resources
		dr.add(Link.of(Constants.tokenUrl).withRel("token"));
		dr.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("cateogries").expand(m));
		dr.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer").expand(m));
		dr.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("register").expand(m));
	
//		verbs / actions
//		dr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag").expand(m));
//		dr.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirm"));
//		dr.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("search"));
//		dr.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("suggest"));
		
		return dr;
	}

}
