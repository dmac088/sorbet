package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.nzbee.domain.promotion.Promotion;

public class PctgDiscount extends Promotion {
	
	private BigDecimal discountPctg;

	public PctgDiscount(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						BigDecimal discountPctg) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt);
		
		this.discountPctg = discountPctg;
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public void setDiscountPctg(BigDecimal discountPctg) {
		this.discountPctg = discountPctg;
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		System.out.println("Computing Percentage Discount Promotion: " + this.promotionCode);
	}

}
