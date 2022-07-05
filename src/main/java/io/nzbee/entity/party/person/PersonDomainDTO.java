package io.nzbee.entity.party.person;

import java.util.Map;

public class PersonDomainDTO {
	
	public static final String ID_ALIAS = "pty_id";
	
	public final String USERNAME_ALIAS = "user_name";
	
	public final String CUSTOMER_NUMBER_ALIAS = "cst_num";
	
	public final String ENABLED_ALIAS = "enabled";
	
	private Long partyId;
	
	private String userName;
	
	private String customerNumber;
	
	private Boolean enabled;
	
	public PersonDomainDTO(
					 String userName,
					 String customerNumber,
					 Boolean enabled) {
		this.userName		= userName;
		this.customerNumber	= customerNumber;
		this.enabled		= enabled;
	}
	
	public PersonDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.partyId		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.userName 		= (tuple[aliasToIndexMap.get(USERNAME_ALIAS)]).toString();
		this.customerNumber = (tuple[aliasToIndexMap.get(CUSTOMER_NUMBER_ALIAS)]).toString();
		this.enabled		= ((Boolean) tuple[aliasToIndexMap.get(ENABLED_ALIAS)]);
	}
	
	public Long getPartyId() {
		return partyId;
	}

	public String getUserName() {
		return userName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
}
