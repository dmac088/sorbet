package io.nzbee.entity.bag.domain.promotion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.bag.item.domain.promotion.PromotionBagItemDomainDTO;

public class PromotionBagDomainDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, PromotionBagDomainDTO> bagDTOMap = new LinkedHashMap<>();

	private Map<String, PromotionBagItemDomainDTO> bagItemDTOMap = new LinkedHashMap<>();

	@Override
	public PromotionBagDomainDTO transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long bagId = ((Number) tuple[aliasToIndexMap.get(PromotionBagDomainDTO.ID_ALIAS)]).longValue();

		PromotionBagDomainDTO bagDTO = bagDTOMap.computeIfAbsent(bagId, bId -> {
			PromotionBagDomainDTO b = new PromotionBagDomainDTO(tuple, aliasToIndexMap);
			return b;
		});

		if (!(tuple[aliasToIndexMap.get(PromotionBagItemDomainDTO.BAG_ITEM_UPC_ALIAS)] == null)) {
			
				String bagItemId = ((String) tuple[aliasToIndexMap.get(PromotionBagItemDomainDTO.BAG_ITEM_UPC_ALIAS)]);

				PromotionBagItemDomainDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
					PromotionBagItemDomainDTO bi = new PromotionBagItemDomainDTO(tuple, aliasToIndexMap);

					return bi;
				});
				bagDTO.getPromotionBagItems().add(bagItemDTO);
			
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
