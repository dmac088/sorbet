package io.nzbee.resources.shipping.type;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.view.shipping.code.ShippingCodeView;

@Component
public class ShippingTypeResourceAssembler extends RepresentationModelAssemblerSupport<ShippingCodeView, ShippingTypeResource> {
	
	public ShippingTypeResourceAssembler() {
		super(ProductController.class, ShippingTypeResource.class);
	}

	@Override
	public ShippingTypeResource toModel(ShippingCodeView product) {
		ShippingTypeResource pr = new ShippingTypeResource(product);
		
		return pr;
	}

}
