package io.nzbee.view.product.shipping.destination;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IShippingDestinationPortService;

public class ShippingDestinationViewServiceImpl implements IShippingDestiantionViewService {

	@Autowired 
	private IShippingDestinationPortService shippingDestinationAdapter;
	
	@Override
	public List<ShippingDestinationView> findByAllShippingDestinations(String locale) {
		return shippingDestinationAdapter.findAll(locale);
	}

}
