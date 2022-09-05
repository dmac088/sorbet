package io.nzbee.domain.promotion;

import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;

public class Promotion implements IProductPromotionType, IBrandPromotionType, ICategoryPromotionType {

	protected final String promotionCode;

	protected final String promotionTypeCode;

	protected final LocalDateTime promotionStartDt;

	protected final LocalDateTime promotionEndDt;

	protected final Boolean active;

	protected final Boolean couponRequired;

	protected final String couponCode;

	protected  ProductUPC productUPC;

	protected  BrandCode brandCode;

	protected  CategoryCode categoryCode;

	public Promotion(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, Boolean active, Boolean couponRequired, String couponCode) {
		this.promotionCode = promotionCode;
		this.promotionTypeCode = promotionTypeCode;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.active = active;
		this.couponRequired = couponRequired;
		this.couponCode = couponCode;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public LocalDateTime getPromotionStartDt() {
		return promotionStartDt;
	}

	public LocalDateTime getPromotionEndDt() {
		return promotionEndDt;
	}

	public String getPromotionTypeCode() {
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

	public String getCouponCode() {
		return couponCode;
	}

	@Override
	public ProductUPC getUPC() {
		return this.productUPC;
	}

	@Override
	public Boolean forUPC(String upc) {
		return this.productUPC.equals(upc);
	}

	@Override
	public BrandCode getBrandCode() {
		return this.brandCode;
	}

	@Override
	public Boolean forBrandCode(String brandCode) {
		return this.brandCode.equals(brandCode); 
	}

	@Override
	public Boolean forCategoryCodes(List<String> categoryCodes) {
		return categoryCodes.contains(this.categoryCode);
	}

	@Override
	public CategoryCode getCategoryCode() {
		return this.categoryCode;
	}

}
