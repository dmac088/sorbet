package io.nzbee.entity.party.address;

import java.util.Map;
import io.nzbee.entity.party.address.type.AddressTypeDTO;
import io.nzbee.entity.party.person.PersonViewDTO;

public class PartyAddressViewDTO {
	
	public static final String ID_ALIAS = "addr_id";
	
	public static final String ADDR_LINE_1_ALIAS = "addr_ln_1";
	
	public static final String ADDR_LINE_2_ALIAS = "addr_ln_2"; 
	
	public static final String ADDR_LINE_3_ALIAS = "addr_ln_3";
	
	public static final String ADDR_COUNTRY_ALIAS = "addr_cnty";
	
	public static final String ADDR_POSTCODE_ALIAS = "addr_pst_cd";
	
	private PersonViewDTO person;
	
	private AddressTypeDTO addressType;
	
	private Long addressId;
	
	private String addressLine1;

	private String addressLine2;
	
	private String addressLine3;
	
	private String country;
	
	private String postcode;
	
	public PartyAddressViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.addressId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.addressLine1	= tuple[aliasToIndexMap.get(ADDR_LINE_1_ALIAS)].toString();
		this.addressLine2	= tuple[aliasToIndexMap.get(ADDR_LINE_2_ALIAS)].toString();
		this.addressLine3	= tuple[aliasToIndexMap.get(ADDR_LINE_3_ALIAS)].toString();
		this.country		= tuple[aliasToIndexMap.get(ADDR_COUNTRY_ALIAS)].toString();
		this.postcode		= tuple[aliasToIndexMap.get(ADDR_POSTCODE_ALIAS)].toString();
	}
	
	public PartyAddressViewDTO(Long addressId, String addressLine1, String addressLine2, String addressLine3,
			String country, String postcode, Long addressTypeId, String addressTypeCode, String addressTypeDesc) {
		super();
		this.addressId 		= addressId;
		this.addressLine1 	= addressLine1;
		this.addressLine2 	= addressLine2;
		this.addressLine3 	= addressLine3;
		this.country 		= country;
		this.postcode 		= postcode;
		this.addressType 	= new AddressTypeDTO(addressTypeId, addressTypeCode, addressTypeDesc);
	}
	
	public PartyAddressViewDTO(Long addressId, String addressLine1, String addressLine2, String addressLine3,
			String country, String postcode, Long addressTypeId, String addressTypeCode, String addressTypeDesc,
			Long personId, String givenName, String familyName, String userName, String customerNumber, Boolean enabled) {
		super();
		this.addressId 		= addressId;
		this.addressLine1 	= addressLine1;
		this.addressLine2 	= addressLine2;
		this.addressLine3 	= addressLine3;
		this.country 		= country;
		this.postcode 		= postcode;
		this.addressType 	= new AddressTypeDTO(addressTypeId, addressTypeCode, addressTypeDesc);
		this.person			= new PersonViewDTO(personId, givenName, familyName, userName, customerNumber, enabled);
	}

	

	public Long getAddressId() {
		return addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public String getCountry() {
		return country;
	}

	public String getPostcode() {
		return postcode;
	}
	
	public AddressTypeDTO getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressTypeDTO addressType) {
		this.addressType = addressType;
	}

	public PersonViewDTO getPerson() {
		return person;
	}

	public void setPerson(PersonViewDTO person) {
		this.person = person;
	}

}
