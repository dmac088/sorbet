package io.nzbee.entity.bag.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.entity.bag.item.domain.BagItemWithQuantityDomainDTO;

public class BagDomainDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, BagDomainDTO> bagDTOMap = new LinkedHashMap<>();

	private Map<Long, BagItemWithQuantityDomainDTO> bagItemDTOMap = new LinkedHashMap<>();
	
	@Override
	public BagDomainDTO transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long bagId = ((Number) tuple[aliasToIndexMap.get(BagDomainDTO.ID_ALIAS)]).longValue();

		BagDomainDTO bagDTO = bagDTOMap.computeIfAbsent(bagId, bId -> {
			BagDomainDTO b = new BagDomainDTO(tuple, aliasToIndexMap);
			return b;
		});

		if (!(tuple[aliasToIndexMap.get(BagItemWithQuantityDomainDTO.ID_ALIAS)] == null)) {

				Long bagItemId = ((Number) tuple[aliasToIndexMap.get(BagItemWithQuantityDomainDTO.ID_ALIAS)]).longValue();

				BagItemWithQuantityDomainDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
				BagItemWithQuantityDomainDTO bi = new BagItemWithQuantityDomainDTO(tuple, aliasToIndexMap);

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
