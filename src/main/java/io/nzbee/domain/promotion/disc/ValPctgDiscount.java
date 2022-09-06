package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;

public class ValPctgDiscount extends Promotion implements IBagPromotion<IDiscountThresholdPromotionPort> {

	private final BigDecimal discountPctg;

	private final BigDecimal dollarThreshold;

	private final String currency;

	private final String direction;
	
	public ValPctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BigDecimal dollarThreshold, String currency, String direction) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
		this.direction = direction;
	}
	
	public ValPctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BigDecimal dollarThreshold, String currency, String direction, ProductUPC productUPC) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt, active, couponRequired, couponCode, productUPC);

		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
		this.direction = direction;
	}
	
	public ValPctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BigDecimal dollarThreshold, String currency, String direction, BrandCode brandCode) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt, active, couponRequired, couponCode, brandCode);

		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
		this.direction = direction;
	}
	
	public ValPctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BigDecimal dollarThreshold, String currency, String direction, CategoryCode categoryCode) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt, active, couponRequired, couponCode, categoryCode);

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
	public void execute(IDiscountThresholdPromotionPort bag) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString());
		System.out.println("for promotion type: " + this.getPromotionTypeCode());
		System.out.println("for item count: " + bag.getDiscountableItems().size());
//		bag.getItems().stream().forEach(bi -> {
//			if (bi.getBagTotalAmount().compareTo(this.getDollarThreshold()) >= 0) {
//				bi.addDiscount(applyDiscount(bi));
//			}
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
