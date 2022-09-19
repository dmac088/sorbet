package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.product.brand.facet.BrandFacetView;

public interface IBrandFacetViewPortService extends IViewPortService<BrandFacetView> {

	BrandFacetView findByCode(Locale locale, String rootCategory, String brandCode);

	List<BrandFacetView> findAll(Locale locale, String categoryCode, Set<String> categoryCodes, Set<String> tagCodes,
			Double maxPrice);

}
