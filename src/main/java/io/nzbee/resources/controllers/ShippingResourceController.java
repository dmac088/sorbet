package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.shipping.ShippingResource;

@RestController
@RequestMapping("/api")
public class ShippingResourceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<ShippingResource> shippingResourceAssembler;

	@GetMapping("/shippingResource")
	public ResponseEntity<ShippingResource> getShippingResource() {
		LOGGER.debug("call " + getClass() + ".getShippingResource()");
		
		ShippingResource dr = shippingResourceAssembler.toModel();
		return ResponseEntity.ok(dr); 
	}
	
	
}
