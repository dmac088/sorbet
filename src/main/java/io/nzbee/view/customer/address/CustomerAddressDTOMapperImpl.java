package io.nzbee.view.customer.address;

import org.springframework.stereotype.Component;
import io.nzbee.entity.party.address.PartyAddressViewDTO;

@Component
public class CustomerAddressDTOMapperImpl implements ICustomerAddressDTOMapper {

	@Override
	public CustomerAddressDTOOut toView(PartyAddressViewDTO d) {
		CustomerAddressDTOOut cdo = new CustomerAddressDTOOut();
		cdo.setAddressLine1(d.getAddressLine1());
		cdo.setAddressLine2(d.getAddressLine2());
		cdo.setAddressLine3(d.getAddressLine3());
		cdo.setCountry(d.getCountry());
		cdo.setPostCode(d.getPostcode());
		cdo.setAddressTypeCode(d.getAddressType().getAddressTypeCode());
		cdo.setAddressTypeDesc(d.getAddressType().getAddressTypeDesc());
		return cdo;
	}

}
