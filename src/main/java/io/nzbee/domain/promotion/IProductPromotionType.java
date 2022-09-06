package io.nzbee.domain.promotion;

import io.nzbee.domain.promotion.value.ProductUPC;

public interface IProductPromotionType {

	ProductUPC getUPC();
	
	Boolean forUPC(ProductUPC upc);
	
}
