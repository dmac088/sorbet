package io.nzbee.keymaps.hkpost.shipping.country;


import java.util.Locale;
import java.util.ResourceBundle;

public class Countries {

    /**
     * Retrieves the value for the messageKey from the locale-specific messages.properties, or from
     * the base messages.properties for unsupported locales.
     *
     * @param messageKey The key for the message in the messages.properties ResourceBundle.
     * @param locale The locale to search the message key.
     * @return The value defined for the messageKey in the provided locale.
     */
    public static String getCountryForLocale(String messageKey, String locale) {

        /*
         * For more complex implementations, you will want a var-args parameter for MessageFormat
         * substitutions. Then we can read the value from the bundle and pass the value with the
         * substitutions to MessageFormat to create the final message value.
         */
        return ResourceBundle.getBundle("countries", Locale.forLanguageTag(locale))
            .getString(messageKey);
    }

}
