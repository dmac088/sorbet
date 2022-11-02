package io.nzbee.entity.product.shipping.entity.shipcode;

public interface IShipCodeSchemaToDTOMapper {

	ShipCodeViewDTO toDTO(ShipCodeFileSchema schema);

}
