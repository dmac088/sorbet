package io.nzbee.view.product.shipping.type;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IShippingTypePortService;

public class ShippingTypeViewServiceImpl implements IShippingTypeViewService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private IShippingTypePortService shippingTypeAdapter;
	
	@Override
	public List<ShippingTypeView> findByAllShippingTypesByDestinationAndWeight(String locale, String shippingDestinationCode, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByAllShippingTypesByDestinationAndWeight with parameter {} ,{}, {}", locale, shippingDestinationCode, userName);
		return shippingTypeAdapter.findAll(locale, shippingDestinationCode, userName);
	}

}
