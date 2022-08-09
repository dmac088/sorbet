package io.nzbee.exceptions;


public class ImageNotFoundException extends GeneralException {

	private static final long serialVersionUID = 1L;

	public ImageNotFoundException(String messageKey, String locale, String objectCode) {
		super(messageKey, locale, objectCode);
	}
    
}
