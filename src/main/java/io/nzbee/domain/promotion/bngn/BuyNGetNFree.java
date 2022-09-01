package io.nzbee.domain.promotion.bngn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IPromotionPort;

public class BuyNGetNFree extends Promotion implements IBagPromotion {
	
	private Long buyQuantity;
	
	private BigDecimal discountPctg;


	public BuyNGetNFree(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						Boolean active,
						Long buyQuantity,
						BigDecimal discountPctg) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active);
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
	public List<DiscountItem> execute(IPromotionPort item) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString() + " for total: " + item.getTotal());
		return null;
	}


	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
