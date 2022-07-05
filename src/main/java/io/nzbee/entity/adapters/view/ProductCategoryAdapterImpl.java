package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOMapper;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOService;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryAdapterImpl implements ICategoryViewPortService {
	
	@Autowired
	private IProductCategoryFacetDTOService productCategoryService;
	
	@Autowired
	private IProductCategoryFacetDTOMapper categoryMapper;
	

	@Override
	@Transactional
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return productCategoryService.findAll(locale, currency, rootCategoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new StringCollectionWrapper(tagCodes), maxPrice)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagcodes) {
		return productCategoryService.getMaxPrice(locale, currency, rootCategoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new StringCollectionWrapper(tagcodes));
	}

	@Override
	@Transactional
	public List<ProductCategoryView> findAll(String locale, String rootCategoryCode) {
		return productCategoryService.findAll(locale, rootCategoryCode)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

}
