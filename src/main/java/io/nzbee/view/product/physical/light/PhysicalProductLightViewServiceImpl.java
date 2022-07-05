package io.nzbee.view.product.physical.light;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.ports.IPhysicalProductLightPortService;

public class PhysicalProductLightViewServiceImpl implements IPhysicalProductLightViewService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPhysicalProductLightPortService physicalProductLightPortService;  
	
	@Override
	public Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameter {}, {}, {}", locale, currency, categoryCode, StringUtils.join(categoryCodes, ','), StringUtils.join(brandCodes, ','), StringUtils.join(tagCodes, ','), maxPrice, page, size, sort);
		return physicalProductLightPortService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}

	@Override
	public Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameter {}, {}, {}", locale, currency, categoryCode, StringUtils.join(categoryCodes.getCodes(), ','), StringUtils.join(brandCodes.getCodes(), ','), StringUtils.join(tagCodes.getCodes(), ','), maxPrice, page, size, sort);
		return physicalProductLightPortService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}

	@Override
	public Page<PhysicalProductLightView> search(String locale, String currency, String categoryCode, int page,
			int size, String sort, String searchTerm, Set<IFacet> selectedFacets, Set<IFacet> returnFacets) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".search() with parameters {}, {}, {}, {}, {}, {}, {}, {}, {}", locale, currency, page, size, sort, searchTerm, StringUtils.join(selectedFacets.stream().map(f -> f.getValue()).collect(Collectors.toList())), StringUtils.join(returnFacets.stream().map(f -> f.getValue()).collect(Collectors.toList()))); 
		return physicalProductLightPortService.search(locale, currency, categoryCode, page, size, sort, searchTerm, selectedFacets, returnFacets);
	}

	@Override
	public Page<PhysicalProductLightView> findAllPhysicalProducts(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAllPhysicalProducts() with parameters {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort); 
		return physicalProductLightPortService.findAllPhysicalProducts(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}

	@Override
	public String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getSuggestion(), with parameters {}, {}, {}, {}", searchTerm, rootCategory, locale, currency);
		return physicalProductLightPortService.getSuggestion(searchTerm, rootCategory, locale, currency);
	}

	@Override
	public List<PhysicalProductLightView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll() with parameters {}. {}. {}, {}", locale, currency, rootCategoryCode, StringUtils.join(codes));
		return physicalProductLightPortService.findAll(locale, currency, rootCategoryCode, codes);
	}

	@Override
	public List<PhysicalProductLightView> findAll(String locale, String currency, Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll() with parameters {}. {}. {}, {}", locale, currency, StringUtils.join(codes));
		return physicalProductLightPortService.findAll(locale, currency, currency, codes);
	}

	@Override
	public PhysicalProductLightView findByDesc(String locale, String currency, String desc) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByDesc() with parameters {}. {}. {}, {}", locale, currency, desc);
		return physicalProductLightPortService.findByDesc(locale, currency, desc);
	}

	@Override
	public PhysicalProductLightView findByCode(String locale, String currency, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode() with parameters {}. {}. {}, {}", locale, currency, code);
		return physicalProductLightPortService.findByCode(locale, currency, code);
	}


}
