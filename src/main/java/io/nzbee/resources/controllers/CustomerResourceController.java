package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.customer.CustomerLinkResource;

@RestController
@RequestMapping("/api")
public class CustomerResourceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<CustomerLinkResource> customerResourceAssembler;
	
	@GetMapping("/customerResource")
	public ResponseEntity<CustomerLinkResource> getCustomerResource() {
		LOGGER.debug("call " + getClass() + ".getCustomerResource()");
		
		CustomerLinkResource dr = customerResourceAssembler.toModel();
		return ResponseEntity.ok(dr);
	}
	
}
