package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.product.physical.light.ProductURIResource;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ProductResourceController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<ProductURIResource> productAssembler;

	
	@GetMapping(value = "/productResource")
    public ResponseEntity<ProductURIResource> getProductURI() {

		LOGGER.debug("Creating product URI"); 
			
    	return ResponseEntity.ok(productAssembler.toModel());
    }
	

}
