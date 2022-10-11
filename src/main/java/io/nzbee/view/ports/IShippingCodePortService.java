package io.nzbee.view.ports;

import java.util.List;
import io.nzbee.view.shipping.code.ShippingCodeView;

public interface IShippingCodePortService {

	
	List<ShippingCodeView> findAll(String locale);

}
