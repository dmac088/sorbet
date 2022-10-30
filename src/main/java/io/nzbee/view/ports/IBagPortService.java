package io.nzbee.view.ports;


import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.bag.BagView;

public interface IBagPortService {

	BagView findByCode(Locale locale, String userName);

} 
