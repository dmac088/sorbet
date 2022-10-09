package io.nzbee.hkpost;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.hkpost")
public class HKPostConfig {

	private String countriesURL;
	
	private String costURL;
	
	private String username;
	
	private String password;

	public String getCountriesURL() {
		return countriesURL;
	}

	public void setCountriesURL(String countriesURL) {
		this.countriesURL = countriesURL;
	}

	public String getCostURL() {
		return costURL;
	}

	public void setCostURL(String costURL) {
		this.costURL = costURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
