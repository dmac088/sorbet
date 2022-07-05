package io.nzbee.view.bag;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.Bag;
import io.nzbee.view.ports.IBagPortService;

public class BagViewServiceImpl implements IBagViewService {

	@Autowired
	private IBagPortService bagService;
	
	@Override
	public BagView findByCode(String locale, String currency, String userName) {
		return bagService.findByCode(locale, currency, userName);
	}

	@Override
	public BagView toView(String locale, String currency, Bag b) {
		return bagService.toview(locale, currency, b);
	}

}
