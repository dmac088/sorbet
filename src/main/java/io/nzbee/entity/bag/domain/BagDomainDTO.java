package io.nzbee.entity.bag.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemWithQuantityDomainDTO;
import io.nzbee.entity.party.person.PersonDomainDTO;

public class BagDomainDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private final Long bagId;
	
	private final Set<RegularBagItemWithQuantityDomainDTO> bagItems  = new HashSet<RegularBagItemWithQuantityDomainDTO>();
	
	private final PersonDomainDTO customer;


	public BagDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.customer = new PersonDomainDTO(tuple, aliasToIndexMap);
	}
	
	public Long getBagId() {
		return bagId;
	}

	public Set<RegularBagItemWithQuantityDomainDTO> getBagItems() {
		return bagItems;
	}

	public PersonDomainDTO getCustomer() {
		return customer;
	}
	
}
