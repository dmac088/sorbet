package io.nzbee.resources.product.physical.full;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

@Relation(collectionRelation="products", itemRelation="product")
public class PhysicalProductFullModel  extends RepresentationModel<PhysicalProductFullModel> {

	private final PhysicalProductFullView data;
	
	public PhysicalProductFullModel(final PhysicalProductFullView product) {
		this.data = product;
	}
	
	public PhysicalProductFullView getData() {
		return data;
	}
	
}
