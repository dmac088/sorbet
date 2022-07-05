package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.product.shipping.attribute.destination.IShippingDestinationViewMapper;
import io.nzbee.entity.product.shipping.attribute.view.IShippingProductAttributeViewService;
import io.nzbee.view.ports.IShippingDestinationPortService;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

public class ShippingDestinationAdapterImpl implements IShippingDestinationPortService{

	@Autowired
	private IShippingProductAttributeViewService shippingAttributeService;
	
	@Autowired
	private IShippingDestinationViewMapper shippingDestinationMapper;
	
	@Override
	@Transactional
	public List<ShippingDestinationView> findAll(String locale) {
		return shippingAttributeService.findAllShippingDestiantion(locale).stream().map(sa -> shippingDestinationMapper.toView(sa)).collect(Collectors.toList());
	}
	
}
