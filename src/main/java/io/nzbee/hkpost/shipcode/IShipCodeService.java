package io.nzbee.hkpost.shipcode;

import java.math.BigDecimal;
import java.util.List;

public interface IShipCodeService {

	List<ShipCodeViewDTO> findAll(String locale, String desitnationCode, BigDecimal weight);

}
