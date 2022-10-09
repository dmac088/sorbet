package io.nzbee.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import io.nzbee.Constants;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.enums.hkpost.country.ShippingCountry;
import io.nzbee.enums.hkpost.type.ShippingTypeInternational;
import io.nzbee.enums.hkpost.type.ShippingTypeLocal;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.hkpost.PostageResponse;
import io.nzbee.keymaps.hkpost.shipping.weight.WeightLimits;
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
	
	@GetMapping("/hkpost/countries")
	public List<ShippingCountryView> getCountries() {
		LOGGER.debug("call " + getClass() + ".getCountries()");
		
		return hkPostAdapter.getCountries();
	}
	
}
