package io.nzbee.resources.search;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.SearchController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class SearchURIResourceAssembler implements ISimpleResourceAssembler<SearchURIResource> {

	@Override
	public SearchURIResource toModel() {
		SearchURIResource sr = new SearchURIResource();
		
		Link l0 = linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("search");
		Link l1 = linkTo(methodOn(SearchController.class).suggest(null, null, null)).withRel("suggest");
		sr.add(l0);
		sr.add(l1);
		return sr;
	}
    
}