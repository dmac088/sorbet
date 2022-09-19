package io.nzbee.view.product.brand.facet;

import java.util.List;
import java.util.Set;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.IViewService;

public interface IBrandFacetViewService extends IViewService<BrandFacetView> {

	BrandFacetView findByCode(Locale locale, String rootCategory, String brandCode);

	List<BrandFacetView> findAll(Locale locale, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes, Double maxPrice);
	
}
