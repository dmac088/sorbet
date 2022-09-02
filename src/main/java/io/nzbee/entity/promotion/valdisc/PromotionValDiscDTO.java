package io.nzbee.entity.promotion.valdisc;

import java.math.BigDecimal;
import java.util.Map;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class PromotionValDiscDTO extends PromotionDomainDTO {
	
	public static final String DISCOUNT_PERCENTAGE_ALIAS = "bag_disc_pctg";
	
	public static final String VALUE_THRESHOLD_ALIAS = "bag_val_tld";
	
	public static final String CURRENCY_ALIAS = "bag_disc_curr";
	
	public static final String DIRECTION_ALIAS = "bag_disc_dir";
	
	
	private final BigDecimal discountPercentage;
	
	private final BigDecimal valueThreshold;
	
	private final String currency;
	
	private final String direction;
	
	public PromotionValDiscDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.discountPercentage = new BigDecimal(((Number) tuple[aliasToIndexMap.get(DISCOUNT_PERCENTAGE_ALIAS)]).doubleValue());
		this.valueThreshold = new BigDecimal(((Number) tuple[aliasToIndexMap.get(VALUE_THRESHOLD_ALIAS)]).doubleValue());
		this.currency = tuple[aliasToIndexMap.get(CURRENCY_ALIAS)].toString();
		this.direction = tuple[aliasToIndexMap.get(DIRECTION_ALIAS)].toString();
		
	}
	
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public BigDecimal getValueThreshold() {
		return valueThreshold;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDirection() {
		return direction;
	}

}