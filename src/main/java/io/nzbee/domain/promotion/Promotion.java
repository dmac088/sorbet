package io.nzbee.domain.promotion;

import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.domain.promotion.value.PromotionCode;
import io.nzbee.domain.promotion.value.PromotionTypeCode;

public class Promotion implements IProductPromotionType, IBrandPromotionType, ICategoryPromotionType {

	protected final PromotionCode promotionCode;

	protected final PromotionTypeCode promotionTypeCode;

	protected final LocalDateTime promotionStartDt;

	protected final LocalDateTime promotionEndDt;

	protected final Boolean active;

	protected final Boolean couponRequired;

	protected final CouponCode couponCode;

	private  ProductUPC productUPC; 

	private  BrandCode brandCode;

	private  CategoryCode categoryCode;

	public Promotion(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, CouponCode couponCode) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
	}
	
	public Promotion(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, CouponCode couponCode, ProductUPC productUPC) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
		this.productUPC = productUPC;
	}
	
	public Promotion(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, CouponCode couponCode, BrandCode brandCode) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
		this.brandCode = brandCode;
	} 
	
	public Promotion(PromotionCode promotionCode, PromotionTypeCode promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, CouponCode couponCode, CategoryCode categoryCode) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
		this.categoryCode = categoryCode;
	}


	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	public LocalDateTime getPromotionStartDt() {
		return promotionStartDt;
	}

	public LocalDateTime getPromotionEndDt() {
		return promotionEndDt;
	}

	public PromotionTypeCode getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public Boolean validate() {
		return true;
	}

	public Boolean getActive() {
		return active;
	}

	public Boolean getCouponRequired() {
		return couponRequired;
	}

	public CouponCode getCouponCode() {
		return couponCode;
	}

	@Override
	public ProductUPC getUPC() {
		return this.productUPC;
	}

	@Override
	public BrandCode getBrandCode() {
		return this.brandCode;
	}
	
	@Override
	public Boolean forUPC(ProductUPC upc) {
		return this.productUPC.sameAs(upc);
	}

	@Override
	public Boolean forBrandCode(BrandCode brandCode) {
		return this.brandCode.sameAs(brandCode); 
	}

	@Override
	public Boolean forCategoryCodes(List<CategoryCode> categoryCodes) {
		return categoryCodes.stream().filter(cc -> this.categoryCode.sameAs(cc)).findAny().isPresent();
	}

	@Override
	public CategoryCode getCategoryCode() {
		return this.categoryCode;
	}

}
