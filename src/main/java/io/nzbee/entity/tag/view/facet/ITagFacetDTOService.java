package io.nzbee.entity.tag.view.facet;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface ITagFacetDTOService  extends ISearchDimensionService<TagFacetDTO> {

	Optional<TagFacetDTO> findByCode(String locale, String code);
	
	Optional<TagFacetDTO> findByCode(String locale, String rootCategory, String code);
	
	List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	List<TagFacetDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes);

	List<TagFacetDTO> findAll(String locale, StringCollectionWrapper codes);

}
