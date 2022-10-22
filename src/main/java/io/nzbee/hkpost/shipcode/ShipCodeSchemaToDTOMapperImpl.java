package io.nzbee.hkpost.shipcode;

import org.springframework.stereotype.Component;

@Component
public class ShipCodeSchemaToDTOMapperImpl implements IShipCodeSchemaToDTOMapper {

	@Override
	public ShipCodeViewDTO toDTO(ShipCodeFileSchema schema) {
		return  new ShipCodeViewDTO(
					schema.getShp_cde(),
					schema.getShp_nme_en(),
					schema.getShp_nme_hk(),
					schema.getShp_nme_cn(),
					schema.getShp_sts(),
					new Double(schema.getShp_max_wgt())
				);
	}
	
}
