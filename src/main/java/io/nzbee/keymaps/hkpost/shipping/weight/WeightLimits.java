package io.nzbee.keymaps.hkpost.shipping.weight;

import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightLimits {

	private static final String propertySuffix = ".max";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeightLimits.class);
    /**
     * Retrieves the value for the messageKey from the locale-specific messages.properties, or from
     * the base messages.properties for unsupported locales.
     *
     * @param messageKey The key for the message in the messages.properties ResourceBundle.
     * @param locale The locale to search the message key.
     * @return The value defined for the messageKey in the provided locale.
     */
    public static String getWeightLimit(String messageKey) {
    	LOGGER.debug("fetching weight limit for key: {}", messageKey + propertySuffix);
        /*
         * For more complex implementations, you will want a var-args parameter for MessageFormat
         * substitutions. Then we can read the value from the bundle and pass the value with the
         * substitutions to MessageFormat to create the final message value.
         */
        return ResourceBundle.getBundle("weightlimits")
            .getString(messageKey + propertySuffix);
    }

}
