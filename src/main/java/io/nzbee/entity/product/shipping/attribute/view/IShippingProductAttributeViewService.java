package io.nzbee.entity.product.shipping.attribute.view;

import java.math.BigDecimal;
import java.util.List;
import io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationDTO;
import io.nzbee.entity.product.shipping.attribute.type.ShippingTypeDTO;

public interface IShippingProductAttributeViewService  {

	List<ShippingDestinationDTO> findAllShippingDestiantion(String locale);

	List<ShippingTypeDTO> findAllShippingTypeByDestinationAndWeight(String locale, String shippingDestination,
			BigDecimal bagWeight);
}