package io.nzbee.view.product.brand.facet;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.ports.IBrandFacetViewPortService;

public class BrandFacetViewServiceImpl implements IBrandFacetViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBrandFacetViewPortService brandService;
	
	@Override
	public List<BrandFacetView> findAll(Locale locale, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll()");
		return brandService.findAll(locale, categoryCode, categoryCodes, tagCodes, maxPrice);
	}

	@Override
	public BrandFacetView findByCode(Locale locale, String rootCategory, String brandCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode()");
		return brandService.findByCode(locale, rootCategory, brandCode);
	}

}
