package io.nzbee.hkpost;

import java.util.HashMap;
import java.util.Map;
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
import io.nzbee.hkpost.country.CountryResponseDTO;

@Component
public class HKPostDaoImpl implements IHKPostDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HKPostConfig config;
	
	@Override
	public PostageResponse getHKPostageFee(	String countryCode, 
											String shipCode, 
											String weight) {
		LOGGER.debug("call " + getClass() + ".getHKPostRequest()");
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(config.getUsername(), config.getPassword()).build();
			
		Map<String, String> params = new HashMap<>();
		params.put("countryCode", countryCode);
		params.put("shipCode", shipCode);
		params.put("weight", weight);
		
		ResponseEntity<PostageResponse> response = null;
		
		try {
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(config.getCostURL()).build();
			
			HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
			
			response = restTemplate.exchange(
											builder.toUriString(), 
											HttpMethod.GET,
											requestEntity,
											PostageResponse.class,
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
	public CountryResponseDTO getCountries() {
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
			
			return response.getBody();
		} catch (RestClientException e) {
			//throw ???????? 
			e.printStackTrace();
		}
		return response.getBody();
	}
	
}
