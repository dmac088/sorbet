package io.nzbee.view.ports;
import java.util.List;

import io.nzbee.view.product.shipping.type.ShippingTypeView;

public interface IShippingTypePortService extends IViewPortService<ShippingTypeView> {

	List<ShippingTypeView> findAll(String locale, String shippingDestination, String bagUsername);

}
