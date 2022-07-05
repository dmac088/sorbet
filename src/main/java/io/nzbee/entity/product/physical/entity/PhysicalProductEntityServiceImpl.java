package io.nzbee.entity.product.physical.entity;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.IProductRepository;
import io.nzbee.entity.product.ProductEntity;


@Service
public class PhysicalProductEntityServiceImpl implements IPhysicalProductEntityService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "productCache";      
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public Optional<ProductEntity> findByCode(String productUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}", productUPC);
		return Optional.ofNullable(productRepository.findByCode(productUPC).get());
	}
	
	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
	})
	public void save(ProductEntity product) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		productRepository.save(product);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #product.productUPC"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #product.productId.toString()")
	})
	public void save(String locale, String currency, ProductEntity product) {
		productRepository.save(product);
	}
	

}