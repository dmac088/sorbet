package io.nzbee.resources.controllers;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.product.physical.light.ProductNavigationResourceDTO;
import io.nzbee.resources.product.physical.light.ProductNavigationURIResource;
import io.nzbee.resources.product.physical.light.ProductResourceDTO;
import io.nzbee.resources.product.physical.light.ProductURIResource;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ProductResourceController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISimpleResourceAssembler<ProductNavigationURIResource, Map<String, String>> productNavigationAssembler;
	
	@Autowired
	private ISimpleResourceAssembler<ProductURIResource, Map<String, String>> productAssembler;
	
	@PostMapping(value = "/productNavigationResource")
    public ResponseEntity<ProductNavigationURIResource> getProductNavigationURI(@RequestBody ProductNavigationResourceDTO dto) {

		LOGGER.debug("Creating search URI with parameters: {}, {}, {}, {}, {}, {}, {}", 
							dto.getLocale(), dto.getCurrency(), dto.getCategory(), dto.getPage(), dto.getSize(), dto.getSort());
    	
		ObjectMapper oMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, String> m = oMapper.convertValue(dto, Map.class);
		
		m.put("locale", dto.getLocale());
		m.put("currency", dto.getCurrency());
		m.put("category", dto.getCategory());
		m.put("page", dto.getPage());
		m.put("size", dto.getSize());
		m.put("sort", dto.getSort()); 
			
    	return ResponseEntity.ok(productNavigationAssembler.toModel(m));
    }
	
	@PostMapping(value = "/productResource")
    public ResponseEntity<ProductURIResource> getProductURI(@RequestBody ProductResourceDTO dto) {

		LOGGER.debug("Creating product URI with parameters: {}, {}, {}", 
							dto.getLocale(), dto.getCurrency(), dto.getCode());
    	
		ObjectMapper oMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, String> m = oMapper.convertValue(dto, Map.class);
		
		m.put("locale", dto.getLocale());
		m.put("currency", dto.getCurrency());
		m.put("id", dto.getCode());
			
    	return ResponseEntity.ok(productAssembler.toModel(m));
    }
	

}
