package io.nzbee.entity.product.physical.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.product.ProductDomainDTO;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PhysicalProductDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, PhysicalProductDTO> physicalProductDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionDomainDTO> promotionDTOMap = new LinkedHashMap<>();
	
	private Map<String, Integer> aliasToIndexMap;
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		
		if (aliasToIndexMap == null) {
            aliasToIndexMap = aliasToIndexMap(aliases);
        }
		
        Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDomainDTO.ID_ALIAS)]).longValue();
        
        PhysicalProductDTO productDTO = physicalProductDTOMap.computeIfAbsent(
            productId,
            id -> {
            	ProductDomainDTO pDto = null;
            	PhysicalProductDTO spDto = new PhysicalProductDTO(tuple, aliasToIndexMap);
            	pDto = spDto.getProductDto();
            	
            			
            	if(!( tuple[aliasToIndexMap.get(PromotionDomainDTO.CODE_ALIAS)] == null)) {
                	
                	Long promotionId = ((Number) tuple[aliasToIndexMap.get(PromotionDomainDTO.CODE_ALIAS)]).longValue();
                	
                	PromotionDomainDTO promotionDTO = promotionDTOMap.computeIfAbsent(
                		promotionId,
            	        pId -> {
            	            PromotionDomainDTO promoDto = new PromotionDomainDTO(tuple, aliasToIndexMap);
            	            
            	            return promoDto;
            	        }
                	);
                	
                	pDto.getPromotions().add(promotionDTO);
                	
                }
                 
            	return spDto;
            }
        );
        return productDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<PhysicalProductDTO> transformList(List collection) {
		return new ArrayList<>(physicalProductDTOMap.values());
	}
	
	
	public Map<String, Integer> aliasToIndexMap(
	        String[] aliases) {
	     
	    Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
	     
	    for (int i = 0; i < aliases.length; i++) {
	        aliasToIndexMap.put(aliases[i], i);
	    }
	     
	    return aliasToIndexMap;
	}

}
