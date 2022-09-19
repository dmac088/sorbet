package io.nzbee.entity.tag.view.facet;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagFacetDTOMapperImpl implements ITagFacetMapper {

	@Override
	public TagFacetView toDo(TagFacetDTO dto) {
		TagFacetView to = new TagFacetView();
		to.setTagCode(dto.getTagCode());
		to.setTagDesc(dto.getTagDesc());
		to.setProductCount(dto.getCount().intValue());
		to.setLocale(dto.getLocale());
		return to;
	}

}
