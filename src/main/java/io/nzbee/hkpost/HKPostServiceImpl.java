package io.nzbee.hkpost;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.hkpost.country.CountryViewDTO;

@Service
public class HKPostServiceImpl implements IHKPostService {

	@Autowired
	private IHKPostDao hkPostDao;

	@Override
	public List<CountryViewDTO> getCountries() {
		return hkPostDao.getCountries();
	}

	@Override
	public PostageProductViewDTO getHKPostageFee(String countryCode, String shipCode, String weight) {
		return hkPostDao.getHKPostageFee(countryCode, shipCode, weight);
	}	
}
