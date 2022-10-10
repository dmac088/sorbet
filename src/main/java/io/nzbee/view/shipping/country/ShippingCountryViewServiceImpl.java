package io.nzbee.view.shipping.country;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IShippingCountryPortService;

public class ShippingCountryViewServiceImpl implements IShippingCountryViewService {

	@Autowired 
	private IShippingCountryPortService shippingDestinationAdapter;
	
	@Override
	public List<ShippingCountryView> findByAllShippingDestinations(String locale) {
		return shippingDestinationAdapter.findAll(locale);
	}

}
