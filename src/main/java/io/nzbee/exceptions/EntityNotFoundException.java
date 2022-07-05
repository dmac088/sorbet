package io.nzbee.exceptions;


public class EntityNotFoundException extends LocalizedException {

	private static final long serialVersionUID = 1L;
	private String objectCode = null;
	
	public EntityNotFoundException(String messageKey, String locale, String objectCode) {
		super(messageKey, locale);
		this.objectCode = objectCode;
	}

	 /**
     * @return a localized message based on the messageKey provided at instantiation.
     */
    public String getLocalizedMessage() {

        /*
         * java.util.logging uses getLocalizedMessage when logging Throwables.
         */
    	String message = Messages.getMessageForLocale(messageKey, locale);
    	
    	String returnMessage = message.replace("{code}", objectCode);
    			
        return returnMessage;
    }

	public String getErrorCode() {
		return exceptionCode;
	}
    
    
}
