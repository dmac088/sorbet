package io.nzbee.entity.product.physical.full;

import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public class PhysicalProductFullViewMapperImpl implements IPhysicalProductFullViewMapper {

	@Override
	public PhysicalProductFullView toView(PhysicalProductFullDTO d) {
		PhysicalProductFullView ppfv = new PhysicalProductFullView();
		ppfv.setProductUPC(d.getProductupc());
		ppfv.setProductDesc(d.getProductdesc());
		ppfv.setProductRetail(d.getRetailprice());
		ppfv.setProductMarkdown(d.getMarkdownprice());
		ppfv.setBrandDesc(d.getBranddesc());
		ppfv.setInStock(d.getInstock());
		ppfv.setProductImage(d.getProductimage());
		ppfv.setProductLongDesc(d.getProductlongdesc());
		return ppfv;
	}

}
