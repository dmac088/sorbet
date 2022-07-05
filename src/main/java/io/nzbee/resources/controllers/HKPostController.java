package io.nzbee.resources.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
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
import io.nzbee.enums.hkpost.country.ShippingCountry;
import io.nzbee.enums.hkpost.type.ShippingTypeInternational;
import io.nzbee.enums.hkpost.type.ShippingTypeLocal;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.hkpost.PostageResponse;
import io.nzbee.keymaps.hkpost.shipping.weight.WeightLimits;

@RestController
@RequestMapping("/api")
public class HKPostController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private final String HKPOST_USERNAME 		= "dmac088";
	private final String HKPOST_PASSWORD 		= "e75c62d3-853d-4e1e-ad9c-97dd93ccbf4e";
	private final String TEST_HKPOST_URL 		= "https://service.hongkongpost.hk/ecshipAPI-trial/version/1/postage/get?countryCode={countryCode}&shipCode={shipCode}&weight={weight}";
	private final Double HKPOST_INCREMENT 		= 0.2;
	private final int 	 HKPOST_ROUNDING 		= 2;
	
	@Autowired
	private IHKPostPort hkPostAdapter;
	
	@GetMapping("/hkpost")
	public ResponseEntity<PostageResponse> getHKPostageFee(	@RequestParam("countryCode") String countryCode, 
															@RequestParam("shipCode") String shipCode, 
															@RequestParam("weight") String weight) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(HKPOST_USERNAME, HKPOST_PASSWORD).build();
			
		Map<String, String> params = new HashMap<>();
		params.put("countryCode", countryCode);
		params.put("shipCode", shipCode);
		params.put("weight", weight);
		
		ResponseEntity<PostageResponse> response = null;
		
		try {
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(TEST_HKPOST_URL).build();
			
			HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
			
			response = restTemplate.exchange(
											builder.toUriString(), 
											HttpMethod.GET,
											requestEntity,
											PostageResponse.class,
											params
											);
			
			return response;
		} catch (RestClientException e) {
			//throw ???????? 
			e.printStackTrace();
		}
		return response;
	}
	
	
	@GetMapping("/RefreshShippingProducts/{locale}")
	public ResponseEntity<String> refreshShippingProducts(@PathVariable String locale) {
		
		ShippingCountry countries[] = ShippingCountry.values();
		
		for(ShippingCountry country: countries) {
			if(country.toString().equals(Constants.hongKongLocalShipCode)) {
				LOGGER.debug("processing country: " + country);
			    for(ShippingTypeLocal type: ShippingTypeLocal.values()) {
			    	writeValues(locale, country.toString(), type.toString());
		        }
			    continue;
			}
			for(ShippingTypeInternational type: ShippingTypeInternational.values()) {
		    	writeValues(locale, country.toString(), type.toString());
	        }
		}
		return ResponseEntity.ok("success");
	}
	
	private void writeValues(String locale, String country, String type) {
		LOGGER.debug("processing type : " + type);
    	//get the current weight limit
    	BigDecimal weightLimit = new BigDecimal(WeightLimits.getWeightLimit(type.toString()));
    	boolean isError = false;
    	Double i = 0.0;
    	
    	while(i+HKPOST_INCREMENT<weightLimit.doubleValue()) {
    	//	LOGGER.debug("weight from: " + new BigDecimal(i+0.01).setScale(HKPOST_ROUNDING, RoundingMode.HALF_DOWN).toString());
    	//	LOGGER.debug("weight to: " + new BigDecimal(i+HKPOST_INCREMENT).setScale(HKPOST_ROUNDING, RoundingMode.HALF_DOWN).toString());
    		isError = persistShippingProductResponse(locale, country.toString(), type.toString(), 
    				new BigDecimal(i+0.01).setScale(HKPOST_ROUNDING, RoundingMode.HALF_DOWN).toString(),
    				new BigDecimal(i+0.20).setScale(HKPOST_ROUNDING, RoundingMode.HALF_DOWN).toString());
    		if(isError) { return; }
    		i += HKPOST_INCREMENT;
    	}
	}
	
	
	private boolean persistShippingProductResponse(String locale, String countryCode, String postageType, String weightFrom, String weightTo) {
		ResponseEntity<PostageResponse>  response = this.getHKPostageFee(countryCode, postageType, weightTo);
		hkPostAdapter.save(locale, response.getBody(), countryCode + "_" + postageType + "_" + weightFrom + "_" + weightTo, weightFrom, weightTo, postageType, countryCode);
		return response.getBody().getTotalPostage() == null;
	}

}
