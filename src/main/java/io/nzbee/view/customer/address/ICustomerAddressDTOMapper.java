package io.nzbee.view.customer.address;

import io.nzbee.entity.party.address.PartyAddressViewDTO;
import io.nzbee.view.IViewObjectMapper;

public interface ICustomerAddressDTOMapper extends IViewObjectMapper<PartyAddressViewDTO, CustomerAddressDTOOut> {

	CustomerAddressDTOOut toView(PartyAddressViewDTO d);

}
