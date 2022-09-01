package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagLevelPromotion;
import io.nzbee.domain.promotion.IRegularBagItemLevelPromotion;
import io.nzbee.domain.promotion.Promotion;

public class PctgDiscount extends Promotion implements IRegularBagItemLevelPromotion, IBagLevelPromotion {
	
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
	public List<DiscountItem> execute(RegularBagItem item) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString() + " for product: " + item.getBagItem().getProductUPC());
		return null;
	}

	@Override
	public List<DiscountItem> execute(Bag bag) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString() + " for bag: " + bag.getCustomer().getUserName());
		return null;
	}

}
