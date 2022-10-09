package io.nzbee.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.hkpost.PostageResponse;
import io.nzbee.view.product.shipping.country.ShippingCountryView;

@RestController
@RequestMapping("/api")
public class HKPostController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IHKPostPort hkPostAdapter;
	
	@GetMapping("/hkpost/postagefee")
	public ResponseEntity<PostageResponse> getHKPostageFee(	@RequestParam("countryCode") String countryCode, 
															@RequestParam("shipCode") String shipCode, 
															@RequestParam("weight") String weight) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		return null;//hkPostAdapter.getHKPostageFee(countryCode, shipCode, weight);
	}
	
	@GetMapping("/hkpost/{locale}/{currency}/countries")
	public List<ShippingCountryView> getCountries(@PathVariable String locale, @PathVariable String currency) {
		LOGGER.debug("call " + getClass() + ".getCountries() with params: {}, {}", locale, currency);
		
		return hkPostAdapter.getCountries(locale, currency);
	}
	
}
