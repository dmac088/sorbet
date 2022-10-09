package io.nzbee.view.ports;
import java.util.List;

import io.nzbee.view.product.shipping.country.ShippingCountryView;

public interface IShippingDestinationPortService extends IViewPortService<ShippingCountryView> {

	List<ShippingCountryView> findAll(String locale);

}
