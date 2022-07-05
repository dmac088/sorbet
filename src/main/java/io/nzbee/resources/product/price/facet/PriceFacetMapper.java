package io.nzbee.resources.product.price.facet;

import org.springframework.stereotype.Component;
import io.nzbee.enums.FacetNameEnum;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;

@Component
public class PriceFacetMapper implements IFacetMapper<Double, EntityFacet> {

	public EntityFacet toEntityFacet(Double price) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName(FacetNameEnum.price);
		ef.setObjectType(double.class.getSimpleName());
		ef.setId(price.toString());
		ef.setDesc(price.toString());
		ef.setHierarchical(false);
		ef.setValue(price.toString());
		ef.setCount(Long.valueOf(0).intValue());
		return ef;
	}

}
