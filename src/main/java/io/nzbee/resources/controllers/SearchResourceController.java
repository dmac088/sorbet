package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.search.SearchURIResource;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SearchResourceController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<SearchURIResource> assembler;
	
	@GetMapping(value = "/searchResource")
    public ResponseEntity<SearchURIResource> getSearchURI() {

		LOGGER.debug("Creating search URI");
    	
    	return ResponseEntity.ok(assembler.toModel()); 
    }
	
	
}
