package io.nzbee.resources.product.physical.light;

public class ProductResourceDTO {
	private String locale;
	private String currency;
	private String code;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCurrency() {
		return currency;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
