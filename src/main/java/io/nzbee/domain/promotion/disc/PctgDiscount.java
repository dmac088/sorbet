package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IPromotionPort;

public class PctgDiscount extends Promotion implements IBagPromotion {
	
	private BigDecimal discountPctg;
	
	public PctgDiscount(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						BigDecimal discountPctg,
						Boolean active) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active);
		
		this.discountPctg = discountPctg;
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public void setDiscountPctg(BigDecimal discountPctg) {
		this.discountPctg = discountPctg;
	}

	@Override
	public List<DiscountItem> execute(IPromotionPort object) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString() + " with total: " + object.getTotal());
		return null;
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
