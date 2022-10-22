package io.nzbee.hkpost.shipcode;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipCodeServiceImpl implements IShipCodeService {

	@Autowired
	private IShipCodeDao shipCodeDao;
	
	@Override
	public List<ShipCodeViewDTO> findAll() {
		return shipCodeDao.getAll();
	}
	
}
