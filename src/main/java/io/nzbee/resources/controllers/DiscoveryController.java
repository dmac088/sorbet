package io.nzbee.resources.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.Globals;
import io.nzbee.resources.discovery.DiscoveryResource;

@RestController
@RequestMapping("/api")
public class DiscoveryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private Globals globals;
	
	@GetMapping("/Discovery/{locale}/{currency}")
	public ResponseEntity<DiscoveryResource> getDiscovery(@PathVariable String locale, @PathVariable String currency) {
		LOGGER.debug("call " + getClass() + ".getDiscovery()");
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("locale", locale);
		m.put("currency", currency);
		m.put("category", globals.getDefaultProductRootCategoryCode());
		
		DiscoveryResource dr = new DiscoveryResource(m);
		return ResponseEntity.ok(dr);
	}
	
}
