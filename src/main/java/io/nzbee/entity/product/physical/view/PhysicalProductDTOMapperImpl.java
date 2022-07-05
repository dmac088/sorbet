package io.nzbee.entity.product.physical.view;

import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public class PhysicalProductDTOMapperImpl implements IPhysicalProductDTOMapper {

	@Override
	public PhysicalProductFullView toView(PhysicalProductDTO d) {
		PhysicalProductFullView p  = new PhysicalProductFullView();
		p.setProductUPC(d.getProductDto().getProductUPC());
		p.setProductDesc(d.getProductDto().getProductDesc());
		p.setProductLongDesc(d.getProductDto().getProductLongDesc());
		p.setProductRetail(d.getProductDto().getRetailPrice());
		p.setProductMarkdown(d.getProductDto().getMarkdownPrice());
		p.setInStock(d.getProductDto().isInStock());
		p.setProductType(d.getProductDto().getProductStatusCode());
		p.setProductImage(d.getProductDto().getProductImage());
		p.setWeight(d.getWeight());
		
		return p;
	}

}
