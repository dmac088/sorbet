package io.nzbee.hkpost.shipcode;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class ShipCodeFileStorageProperties {
	
    private String shipCodeFile;

	public String getShipCodeFile() {
		return shipCodeFile;
	}

	public void setShipCodeFile(String shipCodeFile) {
		this.shipCodeFile = shipCodeFile;
	}
    
}