package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.SearchResourceController;

@Component
public class DiscoveryResourceAssembler  implements ISimpleResourceAssembler<DiscoveryResource, DiscoveryResourceDTO> {

	public DiscoveryResourceAssembler() {
	}

	@Override
	public DiscoveryResource toModel(DiscoveryResourceDTO d) {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("locale", d.getLocale());
		m.put("currency", d.getCurrency());
		
		//discovery root object
		DiscoveryResource dr = new DiscoveryResource();
		
		Boolean hasNulls = (Collections.frequency(m.values(), null) > 0);
		
		Link l0 = Link.of(Constants.tokenUrl).withRel("token");
		Link l1 = linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("categories");
		Link l2 = linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer");
		Link l3 = linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("register");
		Link l4 = linkTo(methodOn(SearchResourceController.class).getSearchURI(null)).withRel("searchResource");
		
		dr.add(l0);
		dr.add(l2);
		dr.add(l4);
		
		if(hasNulls) {
			dr.add(l1);
			dr.add(l3);
			return dr;
		}
		
		//nouns / things / resources
		dr.add(l1.expand(m));
		dr.add(l3.expand(m));
		return dr;
//		verbs / actions
//		dr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag").expand(m));
//		dr.add(linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirm"));

		
		
	}

}
