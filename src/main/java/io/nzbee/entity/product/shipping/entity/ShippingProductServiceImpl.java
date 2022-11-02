package io.nzbee.entity.product.shipping.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class ShippingProductServiceImpl implements IShippingProductService {

	@Autowired 
	private IShippingProductRepository spr;
	
	@Override
	public ShippingProductViewDTO findOne(String locale, String desitnationCode, String shipCode) {
		return spr.findOne(locale, desitnationCode, shipCode);
	}

	@Override
	public ShippingProductEntity findByUpc(String shippingUpc) {
		return spr.findByShippingProductProductUPC(shippingUpc);
	}

}
