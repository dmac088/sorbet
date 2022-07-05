package io.nzbee.view.ports;
import java.util.List;

import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

public interface IShippingDestinationPortService extends IViewPortService<ShippingDestinationView> {

	List<ShippingDestinationView> findAll(String locale);

}
