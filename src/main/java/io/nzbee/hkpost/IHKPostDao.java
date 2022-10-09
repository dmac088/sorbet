package io.nzbee.hkpost;

import io.nzbee.hkpost.country.CountryResponseDTO;

public interface IHKPostDao {
	
	CountryResponseDTO getCountries();

	PostageResponse getHKPostageFee(String countryCode, String shipCode, String weight);

}
