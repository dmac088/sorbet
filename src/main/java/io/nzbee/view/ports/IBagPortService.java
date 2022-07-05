package io.nzbee.view.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.view.bag.BagView;

public interface IBagPortService {

	BagView findByCode(String locale, String currency, String userName);

	BagView toview(String locale, String currency, Bag b);

}
