package io.nzbee.hkpost;

import io.nzbee.domain.valueObjects.Locale;

public interface IHKPostPort {

	void save(Locale locale, PostageResponse postageResponse, String upc, String weightFrom, String weightTo,
			String type, String country);
	
}
