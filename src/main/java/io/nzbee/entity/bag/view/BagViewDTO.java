package io.nzbee.entity.bag.view;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.bag.item.view.BagItemViewDTO;
import io.nzbee.entity.party.person.PersonViewDTO;

public class BagViewDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private Long bagId;
	
	private Set<BagItemViewDTO> bagItems  = new HashSet<BagItemViewDTO>();
	
	private PersonViewDTO customer;

	public BagViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
	}

	public Set<BagItemViewDTO> getBagItems() {
		return bagItems;
	}
	
	public void setBagItems(Set<BagItemViewDTO> bagItems) {
		this.bagItems = bagItems;
	}

	public Long getBagId() {
		return bagId;
	}

	public PersonViewDTO getCustomer() {
		return customer;
	}

	public void setCustomer(PersonViewDTO customer) {
		this.customer = customer;
	}
	
}
