package io.nzbee.resources.search;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.Collections;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.SearchController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class SearchURIResourceAssembler implements ISimpleResourceAssembler<SearchURIResource, Map<String, String>> {

	@Override
	public SearchURIResource toModel(Map<String, String> m) {
		SearchURIResource sr = new SearchURIResource();
		
		Boolean hasNulls = (Collections.frequency(m.values(), null) > 0);
		
		Link l0 = linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("products");
		//Link l1 = linkTo(methodOn(SearchController.class).suggest(null, null, null)).withRel("suggest");
		
		if(hasNulls) {
			sr.add(l0);
		//	sr.add(l1);
			return sr;
		}
		
		sr.add(l0.expand(m));
		//sr.add(l1.expand(m));
		return sr;
	}
    
}