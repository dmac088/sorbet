package io.nzbee.entity.tag.view.facet;

import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.view.product.tag.facet.TagFacetView;

public interface ITagFacetMapper extends IDomainObjectMapper<TagFacetView, TagFacetDTO> {

	TagFacetView toDo(TagFacetDTO b);

}
