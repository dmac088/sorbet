package io.nzbee.view.bag;

import io.nzbee.domain.bag.Bag;

public interface IBagViewService {

	BagView toView(String locale, String currency, Bag b);

	BagView findByCode(String locale, String currency, String userName, Bag bag);
	
}
