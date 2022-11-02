package io.nzbee.hkpost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException; 
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import io.nzbee.hkpost.country.AllowedCountryCodes;
import io.nzbee.hkpost.country.CountryResponseDTO;
import io.nzbee.hkpost.country.CountryViewDTO;

@Component
public class HKPostDaoImpl implements IHKPostDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HKPostConfig config;
	
	@Override
	public PostageProductViewDTO getHKPostageFee(	String countryCode, 
													String shipCode, 
													BigDecimal weight) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(config.getUsername(), config.getPassword()).build();
			
		Map<String, String> params = new HashMap<>();
		params.put("countryCode", countryCode);
		params.put("shipCode", shipCode);
		params.put("weight", Double.toString(weight.doubleValue()));
		
		ResponseEntity<PostageProductViewDTO> response = null;
		
		try {
			
			UriComponentsBuilder builder = UriComponentsBuilder
					  .fromUriString(config.getCostURL())
					  .queryParam("countryCode", countryCode)
				      .queryParam("shipCode", shipCode)
				      .queryParam("weight", weight);
				      
			
			HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
			
			response = restTemplate.exchange(
											builder.toUriString(), 
											HttpMethod.GET,
											requestEntity,
											PostageProductViewDTO.class,
											params
											);
			
			return response.getBody();
		} catch (RestClientException e) {
			//throw ???????? 
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	@Override
	public List<CountryViewDTO> getCountries() {
		LOGGER.debug("call " + getClass() + ".getCountries()");
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(config.getUsername(), config.getPassword()).build();
		
		ResponseEntity<CountryResponseDTO> response = null;
		
		try {
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(config.getCountriesURL()).build();
			
			HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
			
			Map<String, String> params = new HashMap<>();
			
			response = restTemplate.exchange(
											builder.toUriString(), 
											HttpMethod.GET,
											requestEntity,
											CountryResponseDTO.class,
											params
											);
			
			CountryResponseDTO cr = response.getBody();
			
			return cr.getData().stream().filter(c -> EnumUtils.isValidEnum(AllowedCountryCodes.class, c.getHkpCtyCode())).collect(Collectors.toList());
			
		} catch (RestClientException e) {
			//throw ???????? 
			e.printStackTrace();
		}
		
		return new ArrayList<CountryViewDTO>();
	}
	
}
