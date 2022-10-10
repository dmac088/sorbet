package io.nzbee.hkpost.shipcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ShipCodeDaoImpl implements IShipCodeDao {

	@Override
	public List<ShipCodeViewDTO> findAll() {
		List<ShipCodeViewDTO> lsc = new ArrayList<ShipCodeViewDTO>();
		lsc.add(new ShipCodeViewDTO("LEG", "EC-Get", "易寄取", "易寄取", "A"));
		lsc.add(new ShipCodeViewDTO("SMP", "Smart Post", "易送遞", "易送递", "S"));
		lsc.add(new ShipCodeViewDTO("LCP", "Local Courier Post","本地郵政速遞郵件", "本地邮政速递邮件", "A"));
		lsc.add(new ShipCodeViewDTO("LPL", "Local Parcel", "本地包裹", "本地包裹", "A"));
		lsc.add(new ShipCodeViewDTO("LRM", "Local Registered Mail", "本地掛號", "本地挂号", "A"));
		return lsc;
	}
	
}
