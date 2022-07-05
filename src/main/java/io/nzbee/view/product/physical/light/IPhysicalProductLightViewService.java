package io.nzbee.view.product.physical.light;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.IViewService;

public interface IPhysicalProductLightViewService extends IViewService<PhysicalProductLightView> {

	Page<PhysicalProductLightView> findAll(String locale, String currency,
			String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes, Double maxPrice, String page, String size, String sort);

	Page<PhysicalProductLightView> search(String locale, String currency, String categoryCode, int page, int size, String sort, String searchTerm,
			Set<IFacet> selectedFacets, Set<IFacet> returnFacets);

	Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	Page<PhysicalProductLightView> findAllPhysicalProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency);

	List<PhysicalProductLightView> findAll(String locale, String currency, String rootCategoryCode, Set<String> codes);

	List<PhysicalProductLightView> findAll(String locale, String currency, Set<String> codes);

	PhysicalProductLightView findByDesc(String localeengb, String currencyhkd, String string);

	PhysicalProductLightView findByCode(String localeengb, String currencyhkd, String string);
}
