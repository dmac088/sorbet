package io.nzbee.hkpost.shipcode;

public interface IShipCodeSchemaToDTOMapper {

	ShipCodeViewDTO toDTO(ShipCodeFileSchema schema);

}
