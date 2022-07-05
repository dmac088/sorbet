package io.nzbee.view.bag;

import io.nzbee.domain.bag.Bag;

public interface IBagViewService {

	BagView findByCode(String locale, String currency, String userName);

	BagView toView(String locale, String currency, Bag b);
	
}
