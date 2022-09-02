package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IPctgDiscountPromotionPort;

public class PctgDiscount extends Promotion implements IBagPromotion<IPctgDiscountPromotionPort> {
	
	private BigDecimal discountPctg;
	
	public PctgDiscount(String promotionCode, 
						String promotionTypeCode, 
						LocalDateTime promotionStartDt,
						LocalDateTime promotionEndDt,
						BigDecimal discountPctg,
						Boolean active,
						Boolean couponRequired,
						String couponCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		
		this.discountPctg = discountPctg;
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public void setDiscountPctg(BigDecimal discountPctg) {
		this.discountPctg = discountPctg;
	}

	@Override
	public List<DiscountItem> execute(IPctgDiscountPromotionPort object) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString());
		List<DiscountItem> ldi = new ArrayList<DiscountItem>(); 
		object.getItems().stream().forEach(bi -> {
			ldi.add(applyDiscount(bi));
		}); 
		return ldi;
	}

	private DiscountItem applyDiscount(IDiscountableBagItem bi) {
		BigDecimal amount = bi.getTotalAmount().multiply(discountPctg);
		return new DiscountItem(this.promotionCode, bi.getUPC(), amount);
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
