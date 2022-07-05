package io.nzbee.security.events;

import java.util.Locale;
import org.springframework.context.ApplicationEvent;
import io.nzbee.view.customer.CustomerDTOIn;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final CustomerDTOIn customer;
    

    public OnRegistrationCompleteEvent(final CustomerDTOIn customer2, final Locale locale, final String appUrl) {
        super(customer2);
        this.locale = locale;
        this.appUrl = appUrl;
        this.customer = customer2;
    }
    
    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

	public CustomerDTOIn getCustomer() {
		return this.customer;
	}


}
