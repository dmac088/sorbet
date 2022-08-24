package io.nzbee.entity.bag.item.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

public class BagItemDomainDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, BagItemDomainDTO> bagItemDTOMap = new LinkedHashMap<>();
	
	
	@Override
	public BagItemDomainDTO transformTuple(Object[] tuple, String[] aliases) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(bagItemDTOMap.values());
	}

	public Map<String, Integer> aliasToIndexMap(String[] aliases) {

		Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

		for (int i = 0; i < aliases.length; i++) {
			aliasToIndexMap.put(aliases[i], i);
		}

		return aliasToIndexMap;
	}

}
