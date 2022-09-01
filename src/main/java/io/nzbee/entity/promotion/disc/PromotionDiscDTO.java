package io.nzbee.entity.promotion.disc;

import java.math.BigDecimal;
import java.util.Map;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionDiscDTO extends PromotionDomainDTO {
	
	public static final String DISCOUNT_PERCENTAGE_ALIAS = "disc_pctg";
	
	private final BigDecimal discountPercentage;
	
	public PromotionDiscDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.discountPercentage = new BigDecimal(((Number) tuple[aliasToIndexMap.get(DISCOUNT_PERCENTAGE_ALIAS)]).doubleValue());
	}
	
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	

}
