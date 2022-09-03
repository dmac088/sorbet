package io.nzbee.entity.promotion.type;

import java.util.Map;

import io.nzbee.Constants;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionTypeProductDTO implements IPromotionType {

	public static final String UPC_CODE_ALIAS = "prm_upc_cd";
	
	private IPromotionDTO promotion;
	
	private final String upcCode;
	
	public PromotionTypeProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.upcCode = tuple[aliasToIndexMap.get(UPC_CODE_ALIAS)].toString();
	}
	
	public IPromotionDTO getPromotion() {
		return promotion;
	}

	public String getUpcCode() {
		return upcCode;
	}

	@Override
	public String typeCode() {
		return Constants.promotionTypeProduct;
	}

	@Override
	public void setPromotion(IPromotionDTO promotionDTO) {
		this.promotion = promotionDTO;
	} 
}
