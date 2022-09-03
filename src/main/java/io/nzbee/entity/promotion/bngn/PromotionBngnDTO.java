package io.nzbee.entity.promotion.bngn;

import java.math.BigDecimal;
import java.util.Map;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

public class PromotionBngnDTO implements IPromotionDTO {

	public static final String BUY_QUANTITY_ALIAS = "bngn_buy_qty";
	
	public static final String DISCOUNT_PERCENTAGE_ALIAS = "bngn_disc_pctg";
	
	private final PromotionDomainDTO promotion;
	
	private final Long buyQuantity;
	
	private final BigDecimal discountPercentage;
	
	public PromotionBngnDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotion = new PromotionDomainDTO(tuple, aliasToIndexMap);
		this.buyQuantity = ((Number) tuple[aliasToIndexMap.get(BUY_QUANTITY_ALIAS)]).longValue();
		this.discountPercentage =  new BigDecimal(((Number) tuple[aliasToIndexMap.get(DISCOUNT_PERCENTAGE_ALIAS)]).doubleValue());
	}
	
	public Long getBuyQuantity() {
		return buyQuantity;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public PromotionDomainDTO getPromotion() {
		return promotion;
	}

	@Override
	public String getTypeCode() {
		return this.promotion.getTypeCode();
	}

	@Override
	public String getMechanicCode() {
		return Constants.promotionDiscriminatorBNGN;
	}

	@Override
	public Boolean isType(String typeCode) {
		return this.getTypeCode().equals(typeCode);
	}

}
