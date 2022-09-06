package io.nzbee.domain.promotion.bngn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import io.nzbee.Constants;
import io.nzbee.domain.bag.item.BagItemDiscount;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.IBrandPromotionType;
import io.nzbee.domain.promotion.ICategoryPromotionType;
import io.nzbee.domain.promotion.IProductPromotionType;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IBnGnFreePromotionPort;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;

import org.apache.tomcat.util.buf.StringUtils;

public class BuyNGetNFree extends Promotion implements IBagPromotion<IBnGnFreePromotionPort> {

	private Long buyQuantity;

	private BigDecimal discountPctg;

	public BuyNGetNFree(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode, Long buyQuantity,
			BigDecimal discountPctg) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		this.buyQuantity = buyQuantity;
		this.discountPctg = discountPctg;
	}

	public BuyNGetNFree(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode, Long buyQuantity,
			BigDecimal discountPctg, ProductUPC productUPC) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		this.buyQuantity = buyQuantity;
		this.discountPctg = discountPctg;
		this.productUPC = productUPC;
	}
	
	public BuyNGetNFree(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode, Long buyQuantity,
			BigDecimal discountPctg, BrandCode brandCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		this.buyQuantity = buyQuantity;
		this.discountPctg = discountPctg;
		this.brandCode = brandCode;
	}
	
	public BuyNGetNFree(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode, Long buyQuantity,
			BigDecimal discountPctg, CategoryCode categoryCode) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);
		this.buyQuantity = buyQuantity;
		this.discountPctg = discountPctg;
		System.out.println("the value is: " + categoryCode);
		this.categoryCode = categoryCode;
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
	public void execute(IBnGnFreePromotionPort bag) {
		System.out.println("executing promotion: " + this.getClass().getSimpleName().toString());
		System.out.println("for promotion type: " + this.getPromotionTypeCode());
		System.out.println("for total: " + bag.getTotalAmount());
		switch(this.getPromotionTypeCode()) {
				case Constants.promotionTypeProduct: 
					bag.getDiscountableItems().forEach(i -> {
						if (((IProductPromotionType) this).forUPC(i.getUPC())) {
							System.out.println("for product: " + i.getUPC());
							i.addDiscount(applyDiscount(i));
						}
					});
					return;
				case Constants.promotionTypeBrand:
					bag.getDiscountableItems().forEach(i -> {
						if (((IBrandPromotionType) this).forBrandCode(i.getBrandCode())) {
							System.out.println("for brand: " + i.getBrandCode());
							i.addDiscount(applyDiscount(i));
						}
					});
					return;
				case Constants.promotionTypeCategory:
					bag.getDiscountableItems().forEach(i -> {
						System.out.println("Promotion category code: " + this.getCategoryCode().toString());
						if (((ICategoryPromotionType) this).forCategoryCodes(i.getCategoryCodes())) {
							System.out.println("for categories: " + StringUtils.join(i.getCategoryCodes().stream().map(cc -> cc.toString()).collect(Collectors.toList())));
							i.addDiscount(applyDiscount(i));
						}
					});
					return;
				default:
					return;
		}
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
