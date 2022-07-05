package io.nzbee.resources.tag.facet;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="objects", itemRelation="tag")
public class TagFacetModel extends RepresentationModel<TagFacetModel>  {
	
	private final EntityFacet data;
	
	public TagFacetModel(EntityFacet tag) {
		this.data = tag;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
