package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.product.shipping.attribute.type.IShippingTypeViewMapper;
import io.nzbee.entity.product.shipping.attribute.view.IShippingProductAttributeViewService;
import io.nzbee.view.ports.IShippingTypePortService;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

public class ShippingTypeAdapterImpl implements IShippingTypePortService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingProductAttributeViewService shippingAttributeService;
	
	@Autowired 
	private IShippingTypeViewMapper shippingTypeViewMapper;
	
	@Override
	public List<ShippingTypeView> findAll(String locale, String shippingDestinationCode, String bagUsername) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameter {} ,{}, {}", locale, shippingDestinationCode, bagUsername);
		 
		 return shippingAttributeService.findAllShippingTypeByDestinationAndWeight(locale, shippingDestinationCode).stream()
				 	.map(d -> shippingTypeViewMapper.toView(d)).collect(Collectors.toList());
	}

}

