package io.nzbee.view.product.brand.facet;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IBrandFacetViewPortService;

public class BrandFacetViewServiceImpl implements IBrandFacetViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBrandFacetViewPortService brandService;
	
	@Override
	public List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll()");
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}

	@Override
	public BrandFacetView findByCode(String locale, String rootCategory, String brandCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode()");
		return brandService.findByCode(locale, rootCategory, brandCode);
	}

}
