package io.nzbee.hkpost;


public interface IHKPostPort {

	void save(String locale, PostageResponse postageResponse, String upc, String weightFrom, String weightTo,
			String type, String country);
	
}
