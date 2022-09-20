package io.nzbee.view.bag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.ports.IBagPortService;

public class BagViewServiceImpl implements IBagViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagPortService bagService;
	
	@Override
	public BagView findByCode(Locale locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}, {}", locale, userName);
		return bagService.findByCode(locale, userName);
	}

}
