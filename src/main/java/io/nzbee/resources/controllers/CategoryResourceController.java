package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.category.CategoryLinkResource;

@RestController
@RequestMapping("/api")
public class CategoryResourceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<CategoryLinkResource> categoryResourceAssembler;
	
	@GetMapping("/categoryResource")
	public ResponseEntity<CategoryLinkResource> getCategoryResource() {
		LOGGER.debug("call " + getClass() + ".getCategoryResource()");
		
		CategoryLinkResource dr = categoryResourceAssembler.toModel();
		return ResponseEntity.ok(dr);
	}
	
}
