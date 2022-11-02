package io.nzbee.entity.product.shipping.entity;

public interface IShippingProductService {

	ShippingProductViewDTO findOne(String locale, String desitnationCode, String shipCode);

}
