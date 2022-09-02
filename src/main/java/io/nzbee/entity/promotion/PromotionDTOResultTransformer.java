package io.nzbee.entity.promotion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.bngn.PromotionBngnDTO;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;
import io.nzbee.entity.promotion.valdisc.PromotionValDiscDTO;

public class PromotionDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, IPromotionDTO> promotionDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        String promotionId = ((String) tuple[aliasToIndexMap.get(PromotionDomainDTO.CODE_ALIAS)]).toString();
        
        String discriminator = ((String) tuple[aliasToIndexMap.get(PromotionDomainDTO.MECH_CODE_ALIAS)]).toString();
        
        IPromotionDTO promotionDTO = promotionDTOMap.computeIfAbsent(
            promotionId,
            id -> getSubType(discriminator, tuple, aliasToIndexMap)
        );
        
        return promotionDTO;
	}
	
	private IPromotionDTO getSubType(String key, Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		 switch(key) {
			case Constants.promotionDiscriminatorBNGN:
				return new PromotionBngnDTO(tuple, aliasToIndexMap);
			
			case Constants.promotionDiscriminatorDISC:
				return new PromotionDiscDTO(tuple, aliasToIndexMap);
		
			case Constants.promotionDiscriminatorValDISC:
				return new PromotionValDiscDTO(tuple, aliasToIndexMap);
				
			default:
				return new PromotionDomainDTO(tuple, aliasToIndexMap);
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(promotionDTOMap.values());
	}
	
	public  Map<String, Integer> aliasToIndexMap(
	        String[] aliases) {
	     
	    Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
	     
	    for (int i = 0; i < aliases.length; i++) {
	        aliasToIndexMap.put(aliases[i], i);
	    }
	     
	    return aliasToIndexMap;
	}

}
