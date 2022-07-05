package io.nzbee.entity.product.physical.view;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;


@Service
public class PhysicalProductDTOServiceImpl implements IPhysicalProductDTOService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "productCache";      
	
	@Autowired
	private IPhysicalProductDTODao productDAO;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #currency + \", \" + #productId.toString()")
	public Optional<PhysicalProductDTO> findById(String locale, String currency, Long productId) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById parameters : {}, {}, {}", locale, currency, productId);
		return productDAO.findById(locale, currency, productId);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #currency + \", \" + #productUPC")
	public Optional<PhysicalProductDTO> findByCode(String locale, String currency, String productUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}, {}, {}", locale, currency, productUPC);
		return productDAO.findByCode(locale, currency, productUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #currency + \", \" + #productDesc")
	public Optional<PhysicalProductDTO> findByDesc(String locale, String currency, String productDesc) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByDesc parameters : {}, {}, {}", locale, currency, productDesc);
		return productDAO.findByDesc(locale, currency, productDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #productCodes.getCacheKey()")
	public List<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll parameters : {}, {}, {}, {}", locale, currency, rootCategory, productCodes.getCacheKey());
		return productDAO.findAll(locale, currency, rootCategory, productCodes);
	}


}