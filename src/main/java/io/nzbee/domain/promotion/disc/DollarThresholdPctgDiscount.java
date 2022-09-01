package io.nzbee.domain.promotion.disc;

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
	
	public DollarThresholdPctgDiscount(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						BigDecimal discountPctg,
						Boolean active, 
						Boolean couponRequired,
						String couponCode,
						BigDecimal dollarThreshold,
						String currency) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		
		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
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

	private List<DiscountItem> execute(BigDecimal dollarAmount) {
		System.out.println("executing shipping promotion: " + this.getClass().getSimpleName().toString());
		System.out.println("the currency is: " + currency);
		System.out.println("the bag subtotal is: " + dollarAmount);
		System.out.println("the promotion dollar threshold is: " + dollarThreshold);
		
		return null;
	}

	@Override
	public List<DiscountItem> execute(IDiscountThresholdPromotionPort object) {
		System.out.println("executing shipping promotion: " + this.getClass().getSimpleName().toString());
		return this.execute(object.getTotalAmount());
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}


}
