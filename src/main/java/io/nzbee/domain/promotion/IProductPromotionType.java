package io.nzbee.domain.promotion;

public interface IProductPromotionType {

	String getUPC();
	
	Boolean forUPC(String upc);
	
}
