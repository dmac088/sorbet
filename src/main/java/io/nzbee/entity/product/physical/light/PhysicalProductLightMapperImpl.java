package io.nzbee.entity.product.physical.light;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Component(value="physicalProductLightMapper")
public class PhysicalProductLightMapperImpl implements IPhysicalProductLightMapper {

	@Override
	public PhysicalProductLightView toView(PhysicalProductLightDTO d) {
		PhysicalProductLightView pplv = new PhysicalProductLightView();
		pplv.setProductUPC(d.getProductupc());
		pplv.setProductDesc(d.getProductdesc());
		pplv.setProductRetail(d.getRetailprice());
		pplv.setProductMarkdown(d.getMarkdownprice());
		pplv.setBrandDesc(d.getBranddesc());
		pplv.setInStock(d.getInstock());
		pplv.setProductImage(d.getProductimage());
		return pplv;
	}

}
