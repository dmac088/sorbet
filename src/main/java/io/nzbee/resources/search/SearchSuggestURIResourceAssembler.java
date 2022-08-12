package io.nzbee.resources.search;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.Collections;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.SearchController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class SearchSuggestURIResourceAssembler implements ISimpleResourceAssembler<SearchSuggestURIResource, Map<String, String>> {

	@Override
	public SearchSuggestURIResource toModel(Map<String, String> m) {
		SearchSuggestURIResource sr = new SearchSuggestURIResource();
		
		Boolean hasNulls = (Collections.frequency(m.values(), null) > 0);
		
		Link l0 = linkTo(methodOn(SearchController.class).suggest(null, null, null)).withRel("suggest");
		
		if(hasNulls) {
			sr.add(l0);
			return sr;
		}
		
		sr.add(l0.expand(m));
		return sr;
	}
    
}