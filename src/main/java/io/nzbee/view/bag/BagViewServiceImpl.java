package io.nzbee.view.bag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.Bag;
import io.nzbee.view.ports.IBagPortService;

public class BagViewServiceImpl implements IBagViewService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagPortService bagService;
	
	@Override
	public BagView findByCode(String locale, String currency, String userName, Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}, {}, {}", locale, currency, userName);
		return bagService.findByCode(locale, currency, userName, bag);
	}

	@Override
	public BagView toView(String locale, String currency, Bag b) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView with parameters {}, {}, {}", locale, currency, b.getCustomer().getUserName());
		return bagService.toView(locale, currency, b);
	}

}
