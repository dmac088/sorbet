package io.nzbee.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.resources.shipping.country.ShippingCountryResource;
import io.nzbee.resources.shipping.country.ShippingCountryResourceAssembler;
import io.nzbee.resources.shipping.shipcode.ShippingCodeResource;
import io.nzbee.resources.shipping.shipcode.ShippingTypeResourceAssembler;
import io.nzbee.view.shipping.ShippingFeeView;

@RestController
@RequestMapping("/api")
public class ShippingController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IHKPostPort hkPostAdapter;
	
	@Autowired
	private ShippingCountryResourceAssembler countryAssembler;
	
	@Autowired
	private ShippingTypeResourceAssembler typeAssembler;
	
	@Autowired
	private IBagDomainService domainBagService;
	
	@GetMapping("/hkpost/postagefee/{locale}/{currency}")
	public ShippingFeeView getHKPostageFee(	@PathVariable String locale, 
											@PathVariable String currency,
											@RequestParam("countryCode") String countryCode, 
											@RequestParam("shipCode") String shipCode, 
											    Principal principal) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		//we get the weight from the bag, not the front end
		IBag b = domainBagService.findByCode(Locale.localize(locale, currency), new UserName(principal.getName()));
		
		//System.out.println("weight of the bag is " + b.getTotalWeight().amount());
		
		ShippingFeeView f = hkPostAdapter.getHKPostageFee(locale, currency, countryCode, shipCode, b.getTotalWeight().amount());
		
		//System.out.println("postage fee is " + f.getTotalPostage());
		
		return f;
	}
	
	@GetMapping("/hkpost/{locale}/{currency}/countries")
	public CollectionModel<ShippingCountryResource> getCountries(@PathVariable String locale, @PathVariable String currency) {
		LOGGER.debug("call " + getClass() + ".getCountries() with params: {}, {}", locale, currency);
		
		return countryAssembler.toCollectionModel(hkPostAdapter.getCountries(locale, currency));
	}
	
	@GetMapping("/hkpost/{locale}/{currency}/destinationCode/{destinationCode}/shipcodes")
	public CollectionModel<ShippingCodeResource> getShipCodes(@PathVariable String locale, @PathVariable String currency, @PathVariable String destinationCode, Principal principal) {
		LOGGER.debug("call " + getClass() + ".getCountries() with params: {}, {}", locale, currency);
		
		IBag b = domainBagService.findByCode(Locale.localize(locale, currency), new UserName(principal.getName()));
		
		return typeAssembler.toCollectionModel(hkPostAdapter.getShipCodes(locale, currency, destinationCode, b));
		
	}
	
}
