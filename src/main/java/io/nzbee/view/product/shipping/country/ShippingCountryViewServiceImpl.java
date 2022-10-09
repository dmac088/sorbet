package io.nzbee.view.product.shipping.country;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IShippingDestinationPortService;

public class ShippingCountryViewServiceImpl implements IShippingCountryViewService {

	@Autowired 
	private IShippingDestinationPortService shippingDestinationAdapter;
	
	@Override
	public List<ShippingCountryView> findByAllShippingDestinations(String locale) {
		return shippingDestinationAdapter.findAll(locale);
	}

}
