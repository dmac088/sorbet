package io.nzbee.entity.adapters.view;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.IPartyAddressViewService;
import io.nzbee.entity.party.address.PartyAddressViewDTO;
import io.nzbee.entity.party.address.entity.IPartyAddressEntityService;
import io.nzbee.entity.party.address.entity.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.view.customer.address.CustomerAddressDTOIn;
import io.nzbee.view.customer.address.CustomerAddressDTOOut;
import io.nzbee.view.customer.address.ICustomerAddressDTOMapper;
import io.nzbee.view.ports.ICustomerAddressPortService;

@Service
public class AddressAdapter implements ICustomerAddressPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPartyAddressViewService addressViewService;
	
	@Autowired
	private IPartyAddressEntityService addressEntityService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IAddressTypeService addressTypeService;
	
	@Autowired 
	private ICustomerAddressDTOMapper addressMapper;
	
	@Override
	public CustomerAddressDTOOut findByUsernameAndType(String userName, String addressTypeCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByUsername with parameter {}", userName);
		
		Optional<PartyAddressViewDTO> oa = addressViewService.findByUsernameAndRoleAndType(userName, Constants.partyRoleCustomer, addressTypeCode);
		
		PartyAddressViewDTO a = oa.get();
	
		return addressMapper.toView(a);
	}
	
	@Override
	public void save(CustomerAddressDTOIn addressObject, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		Optional<PartyAddressEntity> opa = addressEntityService.findByUsernameAndType(userName, addressObject.getAddressTypeCode());
		
		Optional<AddressTypeEntity> oat = addressTypeService.findByCode(addressObject.getAddressTypeCode());
		
		Optional<Party> op = partyService.findByUsername(userName);
		
		PartyAddressEntity pa = opa.isPresent() 
		? opa.get()
		: new PartyAddressEntity();
		
		pa.setAddressLine1(addressObject.getAddressLine1());
		pa.setAddressLine2(addressObject.getAddressLine2());
		pa.setAddressLine3(addressObject.getAddressLine3());
		pa.setAddressCountry(addressObject.getCountry());
		pa.setAddressPostCode(addressObject.getPostCode());
		pa.setParty(op.get());
		pa.setType(oat.get());
		
		addressEntityService.save(pa);
	}


}
