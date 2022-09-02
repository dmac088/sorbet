package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;

public class DollarThresholdPctgDiscount extends Promotion implements IBagPromotion<IDiscountThresholdPromotionPort> {
	
	private final BigDecimal discountPctg;
	
	private final BigDecimal dollarThreshold;
	
	private final String currency;
	
	private final String direction;
	
	public DollarThresholdPctgDiscount(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						BigDecimal discountPctg,
						Boolean active, 
						Boolean couponRequired,
						String couponCode,
						BigDecimal dollarThreshold,
						String currency,
						String direction) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		
		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
		this.direction = direction;
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public BigDecimal getDollarThreshold() {
		return dollarThreshold;
	}
	
	public String getCurrency() {
		return currency;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public List<DiscountItem> execute(IDiscountThresholdPromotionPort object) {
		System.out.println("executing threshold promotion: " + this.getClass().getSimpleName().toString());
		
		object.getItems().stream().forEach(bi -> {
			System.out.println(bi.getUPC());
		});
		
		
		return null;
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}


}
