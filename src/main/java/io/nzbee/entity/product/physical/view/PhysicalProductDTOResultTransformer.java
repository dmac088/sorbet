package io.nzbee.entity.product.physical.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.category.product.CategoryProductDomainDTO;
import io.nzbee.entity.product.ProductDomainDTO;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicDTO;

public class PhysicalProductDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, PhysicalProductDTO> physicalProductDTOMap = new LinkedHashMap<>();
	
	private Map<Long, CategoryProductDomainDTO> categoryProductDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionDomainDTO> promotionDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionMechanicDTO> promotionMechanicDTOMap = new LinkedHashMap<>();
	
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
            	
            			
            	if(!( tuple[aliasToIndexMap.get(PromotionDomainDTO.ID_ALIAS)] == null)) {
                	
                	Long promotionId = ((Number) tuple[aliasToIndexMap.get(PromotionDomainDTO.ID_ALIAS)]).longValue();
                	
                	PromotionDomainDTO promotionDTO = promotionDTOMap.computeIfAbsent(
                		promotionId,
            	        pId -> {
            	            PromotionDomainDTO promoDto = new PromotionDomainDTO(tuple, aliasToIndexMap);
            	            
            	            Long promotionMechanicId = ((Number) tuple[aliasToIndexMap.get(PromotionMechanicDTO.ID_ALIAS)]).longValue();
                        	
                        	PromotionMechanicDTO promotionMechanic = promotionMechanicDTOMap.computeIfAbsent(
                        		promotionMechanicId,
                        		pMechanicId -> {
                        			PromotionMechanicDTO promoMechDto = new PromotionMechanicDTO(tuple, aliasToIndexMap);
                        			return promoMechDto;
                        		}		
                        	);
                        	
                        	promoDto.setMechanicDTO(promotionMechanic);
            	            
            	            return promoDto;
            	        }
                	);
                	
                	pDto.getPromotions().add(promotionDTO);
                	

                }
                 
            	return spDto;
            }
        );
        
        Long categoryId = ((Number) tuple[aliasToIndexMap.get(CategoryProductDomainDTO.ID_ALIAS)]).longValue();
        
        categoryProductDTOMap.computeIfAbsent(
                categoryId,
                id -> {
                	CategoryProductDomainDTO cDto = new CategoryProductDomainDTO(tuple, aliasToIndexMap);
                	productDTO.getProductDto().getCategories().add(
                            new CategoryProductDomainDTO(tuple, aliasToIndexMap)
                    );
                	return cDto;
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
