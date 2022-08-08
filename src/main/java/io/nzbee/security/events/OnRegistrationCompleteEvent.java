package io.nzbee.security.events;

import org.springframework.context.ApplicationEvent;
import io.nzbee.view.customer.CustomerDTOIn;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final String locale;
    private final String currency;
    private final CustomerDTOIn customer;
    

    public OnRegistrationCompleteEvent(final CustomerDTOIn customer2, final String locale2, String currency, final String appUrl) {
        super(customer2);
        this.locale = locale2;
        this.currency = currency;
        this.appUrl = appUrl;
        this.customer = customer2;
    }
    
    public String getAppUrl() {
        return appUrl;
    }

    public String getLocale() {
        return locale;
    }

	public CustomerDTOIn getCustomer() {
		return this.customer;
	}

	public String getCurrency() {
		return currency;
	}

}
