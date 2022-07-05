package io.nzbee.view.product.shipping.destination;

import java.util.List;
import io.nzbee.view.IViewService;

public interface IShippingDestiantionViewService extends IViewService<ShippingDestinationView> {

	List<ShippingDestinationView> findByAllShippingDestinations(String locale);

}
