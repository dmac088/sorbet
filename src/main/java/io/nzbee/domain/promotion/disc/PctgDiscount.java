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
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.Money;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.domain.promotion.value.PromotionCode;
import io.nzbee.domain.promotion.value.PromotionTypeCode;

public class PctgDiscount extends Promotion implements IBagPromotion<IPctgDiscountPromotionPort> {

	private BigDecimal discountPctg;

	public PctgDiscount(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			CouponCode couponCode) {
		super(promotionCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
	} 

	public PctgDiscount(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			CouponCode couponCode, ProductUPC productUPC) {
		super(promotionCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode, productUPC);

		this.discountPctg = discountPctg;
	}
	
	public PctgDiscount(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			CouponCode couponCode, BrandCode brandCode) {
		super(promotionCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode, brandCode);

		this.discountPctg = discountPctg;
	}
	
	public PctgDiscount(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			CouponCode couponCode, CategoryCode categoryCode) {
		super(promotionCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode, categoryCode);

		this.discountPctg = discountPctg;
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
		Money amount = bi.getTotalAmount().multiply(discountPctg);
		return new BagItemDiscount(bi, amount);
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
