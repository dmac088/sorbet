package io.nzbee.entity.promotion.bngn;

import java.math.BigDecimal;
import java.util.Map;

import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionBngnDTO extends PromotionDomainDTO {

	public static final String BUY_QUANTITY_ALIAS = "bngn_buy_qty";
	
	public static final String DISCOUNT_PERCENTAGE_ALIAS = "bngn_disc_pctg";
	
	private final Long buyQuantity;
	
	private final BigDecimal discountPercentage;
	
	public PromotionBngnDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.buyQuantity = ((Number) tuple[aliasToIndexMap.get(BUY_QUANTITY_ALIAS)]).longValue();
		this.discountPercentage =  new BigDecimal(((Number) tuple[aliasToIndexMap.get(DISCOUNT_PERCENTAGE_ALIAS)]).doubleValue());
	}
	
	public Long getBuyQuantity() {
		return buyQuantity;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	
}
