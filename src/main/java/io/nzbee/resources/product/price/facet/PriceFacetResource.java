package io.nzbee.resources.product.price.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="objects", itemRelation="price")
public class PriceFacetResource extends RepresentationModel<PriceFacetResource>  {
	
	private final EntityFacet data;
	
	public PriceFacetResource(EntityFacet price) {
		this.data = price;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
