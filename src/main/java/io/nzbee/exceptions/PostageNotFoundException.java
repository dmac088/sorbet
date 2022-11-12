package io.nzbee.exceptions;

import io.nzbee.domain.valueObjects.Locale;

public class PostageNotFoundException extends GeneralException {

	private static final long serialVersionUID = 1L;

	public PostageNotFoundException(String messageKey, Locale locale, String objectCode) {
		super(messageKey, locale.getLanguageCode(), objectCode);
	}

}
