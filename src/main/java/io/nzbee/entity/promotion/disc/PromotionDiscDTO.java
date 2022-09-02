package io.nzbee.entity.promotion.disc;

import java.math.BigDecimal;
import java.util.Map;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionDiscDTO implements IPromotionDTO {
	
	public static final String DISCOUNT_PERCENTAGE_ALIAS = "disc_pctg";
	
	private final PromotionDomainDTO promotion;
	
	private final BigDecimal discountPercentage;
	
	public PromotionDiscDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotion = new PromotionDomainDTO(tuple, aliasToIndexMap);
		this.discountPercentage = new BigDecimal(((Number) tuple[aliasToIndexMap.get(DISCOUNT_PERCENTAGE_ALIAS)]).doubleValue());
	}
	
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	
	public PromotionDomainDTO getPromotion() {
		return promotion;
	}
	
	@Override
	public String getType() {
		return this.getPromotion().getPromotionType().typeCode();
	}

}
