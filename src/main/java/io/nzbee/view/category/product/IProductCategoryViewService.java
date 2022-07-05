package io.nzbee.view.category.product;

import java.util.List;
import java.util.Set;
import io.nzbee.view.IViewService;

public interface IProductCategoryViewService extends IViewService<ProductCategoryView> {

	List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes);

	List<ProductCategoryView> findAll(String locale, String rootCategoryCode);
	
}
