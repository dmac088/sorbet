package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.discovery.DiscoveryResource;

@RestController
@RequestMapping("/api")
public class DiscoveryResourceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<DiscoveryResource> discoveryResourceAssembler;
	
	@GetMapping("/discover")
	public ResponseEntity<DiscoveryResource> discover() {
		LOGGER.debug("call " + getClass() + ".discover()");
		
		DiscoveryResource dr = discoveryResourceAssembler.toModel();
		return ResponseEntity.ok(dr);
	}
	
}
