package io.nzbee.hkpost;

import java.math.BigDecimal;
import java.util.List;
import io.nzbee.hkpost.country.CountryViewDTO;

public interface IHKPostService {

	List<CountryViewDTO> getCountries();

	PostageProductViewDTO getHKPostageFee(String destCode, String shipType, BigDecimal amount);

}
