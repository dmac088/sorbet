package io.nzbee.domain.promotion.bngn;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.nzbee.domain.promotion.Promotion;

public class BuyNGetNFree extends Promotion {
	
	private Long buyQuantity;
	
	private BigDecimal discountPctg;

	public BuyNGetNFree(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						Long buyQuantity,
						BigDecimal discountPctg) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt);
		
		this.buyQuantity = buyQuantity;
		this.discountPctg = discountPctg;
	}
	

	public Long getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Long buyQuantity) {
		this.buyQuantity = buyQuantity;
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
		System.out.println("Computing BNGNF Promotion: " + this.promotionCode);
	}

}
