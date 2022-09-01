package io.nzbee.entity.promotion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.bngn.PromotionBngnDTO;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;
import io.nzbee.entity.promotion.valdisc.PromotionValDiscDTO;

public class PromotionDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, PromotionDomainDTO> promotionDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        String promotionId = ((String) tuple[aliasToIndexMap.get(PromotionDomainDTO.CODE_ALIAS)]).toString();
        
        String discriminator = ((String) tuple[aliasToIndexMap.get(PromotionDomainDTO.MECH_CODE_ALIAS)]).toString();
        
        PromotionDomainDTO promotionDTO = promotionDTOMap.computeIfAbsent(
            promotionId,
            id -> (discriminator.equals(Constants.promotionDiscriminatorBNGN)) 
            	  ?	new PromotionBngnDTO(tuple, aliasToIndexMap)
            	  : (discriminator.equals(Constants.promotionDiscriminatorDISC))  
            	  		? new PromotionDiscDTO(tuple, aliasToIndexMap)
            	  		: new PromotionValDiscDTO(tuple, aliasToIndexMap)
        );
        
        return promotionDTO;
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
