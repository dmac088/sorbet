package io.nzbee.entity.product.shipping.entity.shipcode;

import java.util.List;

public interface IShipCodeService {

	List<ShipCodeViewDTO> findAll(String locale, String desitnationCode);

}
