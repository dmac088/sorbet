package io.nzbee.hkpost;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import io.nzbee.hkpost.country.CountryViewDTO;

public interface IHKPostService {

	List<CountryViewDTO> getCountries();

	Optional<PostageProductViewDTO> getHKPostageFee(String destCode, String shipType, BigDecimal amount);

}
