package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.value.BrandCode;

public interface IBrandPromotionType {

	BrandCode getBrandCode();
	
	Boolean forBrandCode(String brandCode);
	
}
