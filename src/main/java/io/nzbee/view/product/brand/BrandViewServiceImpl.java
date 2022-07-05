package io.nzbee.view.product.brand;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IBrandViewPortService;

public class BrandViewServiceImpl implements IBrandViewService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBrandViewPortService brandService;

	@Override
	public List<BrandView> findByAllShippingProviders(String locale) {
		LOGGER.debug("call " + getClass() + ".findByAllShippingProviders() with parameter {}", locale);
		return brandService.findByAllShippingProviders(locale);
	}

}
