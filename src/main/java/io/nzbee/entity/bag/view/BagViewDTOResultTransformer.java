package io.nzbee.entity.bag.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.bag.item.view.BagItemViewDTO;
import io.nzbee.entity.party.person.PersonViewDTO;

public class BagViewDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, BagViewDTO> bagDTOMap = new LinkedHashMap<>();

	private Map<Long, BagItemViewDTO> bagItemDTOMap = new LinkedHashMap<>();

	private Map<Long, PersonViewDTO> customerDTOMap = new LinkedHashMap<>();


	@Override
	public BagViewDTO transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long bagId = ((Number) tuple[aliasToIndexMap.get(BagViewDTO.ID_ALIAS)]).longValue();

		BagViewDTO bagDTO = bagDTOMap.computeIfAbsent(bagId, bId -> {
			BagViewDTO b = new BagViewDTO(tuple, aliasToIndexMap);
			return b;
		});

		Long customerId = ((Number) tuple[aliasToIndexMap.get(PersonViewDTO.ID_ALIAS)]).longValue();

		PersonViewDTO customerDTO = customerDTOMap.computeIfAbsent(customerId, cId -> {
			PersonViewDTO c = new PersonViewDTO(tuple, aliasToIndexMap);
			return c;
		});

		bagDTO.setCustomer(customerDTO);

		if (!(tuple[aliasToIndexMap.get(BagItemViewDTO.ID_ALIAS)] == null)) {

				Long bagItemId = ((Number) tuple[aliasToIndexMap.get(BagItemViewDTO.ID_ALIAS)]).longValue();

				BagItemViewDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
				BagItemViewDTO bi = new BagItemViewDTO(tuple, aliasToIndexMap);

				return bi;
			});
			bagDTO.getBagItems().add(bagItemDTO);
		}

		return bagDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(bagDTOMap.values());
	}

	public Map<String, Integer> aliasToIndexMap(String[] aliases) {

		Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

		for (int i = 0; i < aliases.length; i++) {
			aliasToIndexMap.put(aliases[i], i);
		}

		return aliasToIndexMap;
	}

}
