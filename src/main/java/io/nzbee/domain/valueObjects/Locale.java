package io.nzbee.domain.valueObjects;

import java.util.Currency;

public class Locale {

	private final java.util.Locale locale;
	
	private final Currency currency;
	
	public Locale(java.util.Locale locale2,
				  Currency currency) {
		this.locale = locale2;
		this.currency = currency;
	}

	public java.util.Locale getLocale() {
		return locale;
	}

	public Currency getCurrency() {
		return currency;
	} 
	
	
	public static Locale localize(String locale, String currency) {
		return new Locale(java.util.Locale.forLanguageTag(locale), 
					Currency.getInstance(currency));
	}
	
}
