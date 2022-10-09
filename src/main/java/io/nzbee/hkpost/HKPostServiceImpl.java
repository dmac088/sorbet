package io.nzbee.hkpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.hkpost.country.CountryResponseDTO;

@Service
public class HKPostServiceImpl implements IHKPostService {

	@Autowired
	private IHKPostDao hkPostDao;

	@Override
	public CountryResponseDTO getCountries() {
		return hkPostDao.getCountries();
	}

	@Override
	public PostageResponse getHKPostageFee(String countryCode, String shipCode, String weight) {
		return hkPostDao.getHKPostageFee(countryCode, shipCode, weight);
	}
	
	
	
}
