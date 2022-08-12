package io.nzbee.resources.controllers;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.search.SearchResourceDTO;
import io.nzbee.resources.search.SearchURIResource;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SearchResourceController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<SearchURIResource, Map<String, String>> assembler;
	
	@PostMapping(value = "/searchResource")
    public ResponseEntity<SearchURIResource> getSearchURI(@RequestBody SearchResourceDTO dto) {

		LOGGER.debug("Creating search URI with parameters: {}, {}, {}, {}, {}, {}, {}", 
							dto.getLocale(), dto.getCurrency(), dto.getCategory(), 
							dto.getQ(), dto.getPage(), dto.getSize(), dto.getSort());
    	
		ObjectMapper oMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, String> m = oMapper.convertValue(dto, Map.class);
		
		m.put("locale", dto.getLocale());
		m.put("currency", dto.getCurrency());
		m.put("category", dto.getCategory());
		m.put("page", dto.getPage());
		m.put("size", dto.getSize());
		m.put("sort", dto.getSort());
		m.put("q", dto.getQ());
			
    	return ResponseEntity.ok(assembler.toModel(m));
    }
	
//	@GetMapping(value = "/Search/{locale}/{currency}/Suggest",
//				params = { "q" })
//	public ResponseEntity<String[]> getSuggestions(	@PathVariable 		String locale, 
//													@PathVariable 		String currency, 
//													@RequestParam("q") 	String term) {
//		
//		LOGGER.debug("Searching for suggestions with patameters: {}, {}", locale, term);
//		
//		return new ResponseEntity< >(productService.getSuggestion(term, globals.getDefaultProductRootCategoryCode(), locale, currency), HttpStatus.OK);
//	}
	
}
