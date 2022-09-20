package io.nzbee.exceptions;

import io.nzbee.domain.valueObjects.Locale;

public class EntityNotFoundException extends GeneralException {

	private static final long serialVersionUID = 1L;
 
	public EntityNotFoundException(String messageKey, Locale locale, String objectCode) {
		super(messageKey, locale.getLocale().getISO3Language(), objectCode);
	}

}
