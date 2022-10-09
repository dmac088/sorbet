package io.nzbee.hkpost.country;

import java.util.List;

public class CountryResponseDTO {

	List<CountryViewDTO> data;

	public List<CountryViewDTO> getData() {
		return data;
	}

	public void setData(List<CountryViewDTO> data) {
		this.data = data;
	}
	
}
