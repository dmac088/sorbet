package io.nzbee.entity.category.attribute;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class CategoryAttributeServiceImpl implements ICategoryAttributeService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CategoryAttributeRepository categoryAttributeRepository; 
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttribute(Long id, String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getCategoryAttribute with parameter {}, {}",id, locale);
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(locale, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeEN(Long id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getCategoryAttributeEN with parameter {}",id);
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeHK(Long id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getCategoryAttributeHK with parameter {}",id);
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeZHHK, id);
	}
	
}
