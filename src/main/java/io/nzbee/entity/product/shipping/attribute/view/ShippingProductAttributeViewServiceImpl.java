package io.nzbee.entity.product.shipping.attribute.view;

import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationDTO;
import io.nzbee.entity.product.shipping.attribute.type.ShippingTypeDTO;

@Service
public class ShippingProductAttributeViewServiceImpl implements IShippingProductAttributeViewService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingProductAttributeViewRepository productAttributeRepository; 
	
	@Override
	public List<ShippingDestinationDTO> findAllShippingDestiantion(String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAllShippingDestiantion with parameter {}", locale);
		return productAttributeRepository.findAllShippingDestiantion(locale);
	}
	
	@Override
	public List<ShippingTypeDTO> findAllShippingTypeByDestinationAndWeight(String locale, String shippingDestination,
			BigDecimal bagWeight) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAllShippingTypeByDestinationAndWeight with parameter {}, {}, {}", locale, shippingDestination, bagWeight);
		return productAttributeRepository.findAllShippingType(locale, shippingDestination, bagWeight);
	}

}
