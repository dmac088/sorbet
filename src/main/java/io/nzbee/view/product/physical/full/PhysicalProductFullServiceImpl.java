package io.nzbee.view.product.physical.full;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IPhysicalProductFullPortService;

public class PhysicalProductFullServiceImpl implements IPhysicalProductFullService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPhysicalProductFullPortService physicalProductFullPortService;  
	
	@Override
	public Optional<PhysicalProductFullView> findByCode(String locale, String currency, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}, {}", locale, currency, code);
		return physicalProductFullPortService.findByCode(locale, currency, code);
	}

}
