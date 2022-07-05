package io.nzbee.keymaps.hkpost.shipping.country;

public class LocalizedCountry {

    private final String messageKey;
    private final String locale;
    private final String code;
    
    private final static String propertySuffix = ".description";

    public LocalizedCountry(String messageKey, String locale) {
        this.messageKey = messageKey + propertySuffix;
        this.locale 	= locale;
        this.code		= messageKey;
    }

    /**
     * @return a localized message based on the messageKey provided at instantiation.
     */
    public String getCountry() {

        /*
         * This is a deliberate role reversal of the default implementation of getLocalizedMessage.
         * some logging frameworks like Log4J 1 & 2 and Logback will use getMessage instead of
         * getLocalizedMessage when logging Throwables. If we want to use these frameworks in client
         * applications to log localized messages, then we'll need to override getMessage in a
         * similar fashion to return the appropriate content.  Or, you can call getLocalizedMessage
         * on your own to create the log content.
         */
        return getLocalizedCountry();
    }

    /**
     * @return a localized message based on the messageKey provided at instantiation.
     */
    public String getLocalizedCountry() {

        /*
         * java.util.logging uses getLocalizedMessage when logging Throwables.
         */
        return Countries.getCountryForLocale(messageKey, locale);
    }

	public String getCode() {
		return code;
	}
}