package io.nzbee.entity.bag.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTO;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;

public class BagDomainDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, BagDomainDTO> bagDTOMap = new LinkedHashMap<>();

	private Map<String, RegularBagItemDomainDTO> bagItemDTOMap = new LinkedHashMap<>();

	private ShippingBagItemDomainDTO shippingBagItem;

	@Override
	public BagDomainDTO transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long bagId = ((Number) tuple[aliasToIndexMap.get(BagDomainDTO.ID_ALIAS)]).longValue();

		BagDomainDTO bagDTO = bagDTOMap.computeIfAbsent(bagId, bId -> {
			BagDomainDTO b = new BagDomainDTO(tuple, aliasToIndexMap);
			return b;
		});

		if (!(tuple[aliasToIndexMap.get(RegularBagItemDomainDTO.BAG_ITEM_UPC_ALIAS)] == null)) {
			if (tuple[aliasToIndexMap.get(RegularBagItemDomainDTO.BAG_ITEM_TYPE_CODE_ALIAS)]
					.equals(Constants.regularBagItemType)) {
				String bagItemId = ((String) tuple[aliasToIndexMap.get(RegularBagItemDomainDTO.BAG_ITEM_UPC_ALIAS)]);

				RegularBagItemDomainDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
					RegularBagItemDomainDTO bi = new RegularBagItemDomainDTO(tuple, aliasToIndexMap);

					return bi;
				});
				bagDTO.getRegularBagItems().add(bagItemDTO);
			}
			if (tuple[aliasToIndexMap.get(RegularBagItemDomainDTO.BAG_ITEM_TYPE_CODE_ALIAS)]
					.equals(Constants.shippingBagItemType)) {
				shippingBagItem = new ShippingBagItemDomainDTO(tuple, aliasToIndexMap);

				bagDTO.setShippingBagItem(shippingBagItem);
			}
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
