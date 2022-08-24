package io.nzbee.entity.bag.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.nzbee.entity.party.person.PersonDomainDTO;

public class BagDomainDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private final Long bagId;
	
	private final Set<BagDomainItemDTO> bagItems  = new HashSet<BagDomainItemDTO>();
	
	private final PersonDomainDTO customer;


	public BagDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.customer = new PersonDomainDTO(tuple, aliasToIndexMap);
	}
	
	public Long getBagId() {
		return bagId;
	}

	public Set<BagDomainItemDTO> getBagItems() {
		return bagItems;
	}

	public PersonDomainDTO getCustomer() {
		return customer;
	}
	
}
