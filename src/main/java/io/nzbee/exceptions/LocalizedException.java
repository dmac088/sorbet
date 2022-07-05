package io.nzbee.exceptions;

public class LocalizedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    protected final String messageKey;
    protected final String exceptionCode;
    protected final String locale;
    
    private final static String propertySuffix = ".exception";

    public LocalizedException(String messageKey, String locale) {
        this.messageKey = messageKey + propertySuffix;
        this.exceptionCode = messageKey;
        this.locale = locale;
    }

	/**
     * @return a localized message based on the messageKey provided at instantiation.
     */
    public String getMessage() {

        /*
         * This is a deliberate role reversal of the default implementation of getLocalizedMessage.
         * some logging frameworks like Log4J 1 & 2 and Logback will use getMessage instead of
         * getLocalizedMessage when logging Throwables. If we want to use these frameworks in client
         * applications to log localized messages, then we'll need to override getMessage in a
         * similar fashion to return the appropriate content.  Or, you can call getLocalizedMessage
         * on your own to create the log content.
         */
        return getLocalizedMessage();
    }

    /**
     * @return a localized message based on the messageKey provided at instantiation.
     */
    public String getLocalizedMessage() {

        /*
         * java.util.logging uses getLocalizedMessage when logging Throwables.
         */		
        return Messages.getMessageForLocale(messageKey, locale);
    }
    
   
}