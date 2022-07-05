package io.nzbee.view.product.tag.facet;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.view.ports.ITagFacetViewPortService;

public class TagFacetViewServiceImpl implements ITagFacetViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private ITagFacetViewPortService tagService;

	@Override
	public List<TagFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categories, Set<String> brands, Double maxPrice) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll() with parameters {}. {}. {}, {}, {}, {}", locale, currency, categoryCode, categories, brands, maxPrice);
		return tagService.findAll(locale, currency, categoryCode, categories, brands, maxPrice);
	}

}
