package io.nzbee.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import io.nzbee.domain.promotion.dto.in.CouponDTO;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;
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
		Bag b = domainBagService.findByCode(locale, currency, principal.getName());
		
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
