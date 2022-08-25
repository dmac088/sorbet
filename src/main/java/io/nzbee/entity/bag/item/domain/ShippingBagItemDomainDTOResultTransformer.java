package io.nzbee.entity.bag.item.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.Constants;

public class ShippingBagItemDomainDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<String, ShippingBagItemDomainDTO> bagItemDTOMap = new LinkedHashMap<>();

	@Override
	public ShippingBagItemDomainDTO transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		String bagItemId = ((String) tuple[aliasToIndexMap.get(ShippingBagItemDomainDTO.BAG_ITEM_UPC_ALIAS)]);

		if (((String) tuple[aliasToIndexMap.get(ShippingBagItemDomainDTO.BAG_ITEM_TYPE_CODE_ALIAS)])
				.equals(Constants.shippingBagItemType)) {

			ShippingBagItemDomainDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
				ShippingBagItemDomainDTO bi = new ShippingBagItemDomainDTO(tuple, aliasToIndexMap);
				return bi;
			});

			return bagItemDTO;
		}
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
