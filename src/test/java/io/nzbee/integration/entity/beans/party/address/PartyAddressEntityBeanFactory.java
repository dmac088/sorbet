package io.nzbee.integration.entity.beans.party.address;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.entity.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.IPersonService;

@Service

public class PartyAddressEntityBeanFactory implements IPartyAddressEntityBeanFactory {

	@Autowired 
    private IPersonService personService;
    
    @Autowired 
    private IAddressTypeService addressTypeService;
	
	@Override
	public PartyAddressEntity getBean() {
		
		AddressTypeEntity addressType = addressTypeService.findByCode("BIL01").get();
		Party customer = personService.findByUsernameAndRole("bob@bob", Constants.partyRoleCustomer).get().getPersonParty();
		
		final PartyAddressEntity address = new PartyAddressEntity();
		
		address.setAddressLine1("Test address line 1");
		address.setAddressLine2("Test address line 2");
		address.setAddressLine3("Test address line 3");
		address.setAddressCountry("Test Country");
		address.setAddressPostCode("Test PC");
		address.setType(addressType);
	
		address.setParty(customer);
		return address;
	}

}
