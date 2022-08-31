package io.nzbee.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;

@RestController
@RequestMapping("/api")
public class PromotionController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagDomainService domainBagService;
	
	@Autowired 
	private IPromotionService promotionService;

	
	@PostMapping("/Promotion/{locale}/{currency}")
	public ResponseEntity<PhysicalProductFullModel> getPromotionDiscounts(@PathVariable String locale, 
																		  @PathVariable String currency,
																		  Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getPromotionDiscounts with parameter {}, {}", locale, currency);
		
		
		//we could just hydrate the Bag domian model and pass to the promotion service 
		Bag b = domainBagService.findByCode(locale, currency, principal.getName());
		
		promotionService.applyAll(b);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/Promotion/{locale}/{currency}/Code/Validate/{code}")
	public GenericResponse validateCouponCode(  	  @PathVariable String locale, 
														  @PathVariable String currency,
														  @PathVariable String code) {
		
		
		promotionService.validateCouponCode(locale, currency, code);
		
		return new GenericResponse(Constants.genericResponseSuccess);
	}

	
}
