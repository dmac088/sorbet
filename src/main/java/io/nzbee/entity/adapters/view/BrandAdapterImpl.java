package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.brand.view.IBrandDTOMapper;
import io.nzbee.entity.brand.view.IBrandDTOService;
import io.nzbee.view.ports.IBrandViewPortService;
import io.nzbee.view.product.brand.BrandView;
 
public class BrandAdapterImpl implements IBrandViewPortService {
	
	@Autowired
	private IBrandDTOService brandViewService;
	
	@Autowired
	private IBrandDTOMapper brandMapper;
	
	@Override
	public List<BrandView> findByAllShippingProviders(String locale) {
		return brandViewService.findByAllShippingProviders(locale)
				.stream().map(b -> brandMapper.toView(b)).collect(Collectors.toList());
	}

	
}
