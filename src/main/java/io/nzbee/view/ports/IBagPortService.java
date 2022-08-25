package io.nzbee.view.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.view.bag.BagView;

public interface IBagPortService {

	BagView toView(String locale, String currency, Bag b);

	BagView findByCode(String locale, String currency, String userName, Bag bag);

}
