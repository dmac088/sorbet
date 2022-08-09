package io.nzbee.exceptions;


public class EntityNotFoundException extends GeneralException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String messageKey, String locale, String objectCode) {
		super(messageKey, locale, objectCode);
	}

}
