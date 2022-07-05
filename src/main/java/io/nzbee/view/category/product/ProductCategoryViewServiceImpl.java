package io.nzbee.view.category.product;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryViewServiceImpl implements IProductCategoryViewService {

	@Autowired
	private ICategoryViewPortService categoryService;
	
	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return categoryService.findAll(locale, currency, rootCategoryCode, categoryCodes, brandCodes, tagCodes, maxPrice);
	}

	@Override
	public Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes) {
		return categoryService.getMaxPrice(locale, currency, rootCategoryCode, categoryCodes, brandCodes, tagCodes);
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String rootCategoryCode) {
		return categoryService.findAll(locale, rootCategoryCode);
	}

	
	
}
