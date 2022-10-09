package io.nzbee.hkpost;

import java.util.List;
import io.nzbee.hkpost.country.CountryViewDTO;

public interface IHKPostDao {
	
	List<CountryViewDTO> getCountries();

	PostageResponse getHKPostageFee(String countryCode, String shipCode, String weight);

}
