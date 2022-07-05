package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.Globals;
import io.nzbee.resources.discovery.DiscoveryResource;

@RestController
@RequestMapping("/api")
public class DiscoveryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Globals globalVars;
	
	@GetMapping("/Discovery")
	public ResponseEntity<DiscoveryResource> getDiscovery() {
		LOGGER.debug("call " + getClass() + ".getDiscovery()");
		DiscoveryResource dr = new DiscoveryResource(globalVars.getBaseURL());
		return ResponseEntity.ok(dr);
	}
	
}
