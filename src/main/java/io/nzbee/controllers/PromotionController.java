package io.nzbee.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.dto.promotion.in.BagDTO;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;

@RestController
@RequestMapping("/api")
public class PromotionController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private Globals globalVars;

	
	@PostMapping("/Promotion/{locale}/{currency}")
	public ResponseEntity<PhysicalProductFullModel> getPromotionDiscounts(@PathVariable String locale, 
																		  @PathVariable String currency,
																		  @RequestBody BagDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getPromotionDiscounts with parameter {}, {}", locale, currency);
		
		System.out.println(dto.getCoupon());
		System.out.println(dto.getItems().size());
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
