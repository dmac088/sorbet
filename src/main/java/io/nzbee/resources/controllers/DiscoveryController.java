package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.nzbee.resources.discovery.DiscoveryResource;
import io.nzbee.resources.discovery.ISimpleResourceAssembler;
import io.nzbee.resources.discovery.LocaliseDTO;

@RestController
@RequestMapping("/api")
public class DiscoveryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<DiscoveryResource> discoveryResourceAssembler;
	
	@PostMapping("/discover")
	public ResponseEntity<DiscoveryResource> discover(@RequestBody LocaliseDTO l) {
		LOGGER.debug("call " + getClass() + ".discover()");
		
		DiscoveryResource dr = discoveryResourceAssembler.toModel(l);
		return ResponseEntity.ok(dr);
	}
	
}
