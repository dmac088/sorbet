package io.nzbee.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.resources.shipping.country.ShippingCountryResource;
import io.nzbee.resources.shipping.country.ShippingCountryResourceAssembler;
import io.nzbee.resources.shipping.type.ShippingTypeResource;
import io.nzbee.resources.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.view.shipping.ShippingProductView;

@RestController
@RequestMapping("/api")
public class HKPostController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IHKPostPort hkPostAdapter;
	
	@Autowired
	private ShippingCountryResourceAssembler countryAssembler;
	
	@Autowired
	private ShippingTypeResourceAssembler typeAssembler;
	
	@GetMapping("/hkpost/postagefee/{locale}/{currency}")
	public ShippingProductView getHKPostageFee(	@PathVariable String locale, 
												@PathVariable String currency,
												@RequestParam("countryCode") String countryCode, 
											    @RequestParam("shipCode") String shipCode, 
												@RequestParam("weight") String weight) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		return hkPostAdapter.getHKPostageFee(locale, currency, countryCode, shipCode, weight);
	}
	
	@GetMapping("/hkpost/{locale}/{currency}/countries")
	public CollectionModel<ShippingCountryResource> getCountries(@PathVariable String locale, @PathVariable String currency) {
		LOGGER.debug("call " + getClass() + ".getCountries() with params: {}, {}", locale, currency);
		
		return countryAssembler.toCollectionModel(hkPostAdapter.getCountries(locale, currency));
	}
	
	@GetMapping("/hkpost/{locale}/{currency}/shipcodes")
	public CollectionModel<ShippingTypeResource> getShipCodes(@PathVariable String locale, @PathVariable String currency) {
		LOGGER.debug("call " + getClass() + ".getCountries() with params: {}, {}", locale, currency);
		
		return typeAssembler.toCollectionModel(hkPostAdapter.getShipCodes(locale, currency));
	}
	
}
