package io.nzbee.entity.product.shipping.entity.shipcode;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipCodeServiceImpl implements IShipCodeService {

	@Autowired
	private IShipCodeRepository shipCodeRepository;
		
	@Override
	public List<ShipCodeViewDTO> findAll(String locale, String desitnationCode, BigDecimal weight) {
		return shipCodeRepository.findAllForDestinationCodeAndWeight(locale, desitnationCode, weight);
	}

	
}
