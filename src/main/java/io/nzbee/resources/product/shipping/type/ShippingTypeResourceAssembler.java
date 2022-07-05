package io.nzbee.resources.product.shipping.type;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

@Component
public class ShippingTypeResourceAssembler extends RepresentationModelAssemblerSupport<ShippingTypeView, ShippingTypeResource> {
	
	public ShippingTypeResourceAssembler() {
		super(ProductController.class, ShippingTypeResource.class);
	}

	@Override
	public ShippingTypeResource toModel(ShippingTypeView product) {
		ShippingTypeResource pr = new ShippingTypeResource(product);
		
		return pr;
	}
}
