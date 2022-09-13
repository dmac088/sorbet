package io.nzbee.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.dto.coupon.CouponDTO;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.bag.IBagViewService;

@RestController
@RequestMapping("/api")
public class PromotionController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagViewService viewBagService;
	
	@Autowired
	private IBagDomainService domainBagService;
	
	@Autowired 
	private IPromotionService promotionService;
	
	@Autowired
	private BagResourceAssembler bagResourceAssembler;

	
	@PostMapping("/Promotion/{locale}/{currency}")
	public ResponseEntity<BagResource> getPromotionDiscounts(@PathVariable String locale, 
															 @PathVariable String currency,
															 Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getPromotionDiscounts with parameter {}, {}", locale, currency);
		
		
		//we could just hydrate the Bag domian model and pass to the promotion service 
		//Bag b = domainBagService.findByCode(locale, currency, principal.getName());
		
		//instead of hydrating the bag domain model we hydrate the promotion domain model 
		
		//use the promotion service to fetch the full list of promotion items relevant to the bag in context 
		//then pass the list of promotion items as a parameter to the service layer and execute the relevant promotions 
		//this is instead of passing the entire bag from our default domain model
		
		//We need to hydrate PromotionItem and Promotion object separately 
		//The new promotionItem service allow us to hydrate PromotionItem with attributes not available in the Cart model.
		
		
		//get the promotion items
		//hydrate the promotion domain model
		//injec the promotion items into the promotion domain model 
		//execute the 
		
		
		promotionService.applyAll(b);
		
		// get the view containing additional attributes
		BagView bv = viewBagService.toView(locale, currency, b);
		
		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}
	
	@PostMapping("/Promotion/{locale}/{currency}/Code/Validate")
	public GenericResponse validateCouponCode(  	  	  @PathVariable String locale, 
														  @PathVariable String currency,
														  @RequestBody CouponDTO dto) {
		
		
		Promotion p = promotionService.findByCouponCode(dto.getCoupon());
		
		p.validate();
		
		return new GenericResponse(Constants.genericResponseSuccess);
	}

	
}
