package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IPctgDiscountPromotionPort;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;

public class PctgDiscount extends Promotion implements IBagPromotion<IPctgDiscountPromotionPort> {

	private BigDecimal discountPctg;

	public PctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
	}

	public PctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, ProductUPC productUPC) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
		this.productUPC = productUPC.getValue();
	}
	
	public PctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BrandCode brandCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
		this.brandCode = brandCode.getValue();
	}
	
	public PctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, CategoryCode categoryCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
		this.categoryCode = categoryCode.getValue();
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public void setDiscountPctg(BigDecimal discountPctg) {
		this.discountPctg = discountPctg;
	}

	@Override
	public void execute(IPctgDiscountPromotionPort bag) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString());
		System.out.println("for promotion type: " + this.getPromotionTypeCode());
		System.out.println("for item count: " + bag.getDiscountableItems().size());
//		object.getItems().stream().forEach(bi -> {
//			bi.addDiscount(applyDiscount(bi));
//		}); 
	}

	private BagItemDiscount applyDiscount(IDiscountableBagItem bi) {
		BigDecimal amount = bi.getTotalAmount().multiply(discountPctg);
		return new BagItemDiscount(bi, amount);
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
