package io.nzbee.view.bag;

import io.nzbee.domain.valueObjects.Locale;

public interface IBagViewService {

	BagView findByCode(Locale locale, String userName);

}
