package io.nzbee.resources.search;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.SearchController;
import io.nzbee.resources.discovery.ISimpleResourceAssembler;

@Component
public class SearchURIResourceAssembler implements ISimpleResourceAssembler<SearchURIResource, SearchResourceDTO> {

	@Override
	public SearchURIResource toModel(SearchResourceDTO l) {
		SearchURIResource sr = new SearchURIResource();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("locale", l.getLocale());
		m.put("currency", l.getCurrency());
		m.put("category", l.getCategory());
		m.put("page", l.getPage());
		m.put("size", l.getSize());
		m.put("sort", l.getSort());
		m.put("q", l.getTerm());
		
		sr.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("search").expand(m));
		sr.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("suggest").expand(m));
		return sr;
	}
    
}