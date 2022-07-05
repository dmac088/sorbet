package io.nzbee.view.ports;
import java.math.BigDecimal;
import io.nzbee.view.product.shipping.ShippingProductView;

public interface IShippingProductPortService extends IViewPortService<ShippingProductView> {

	ShippingProductView findByDestinationAndTypeAndBagWeight(String locale, String currency, String code, String type,
			BigDecimal totalWeight);

}
