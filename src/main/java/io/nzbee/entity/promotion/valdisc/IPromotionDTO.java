package io.nzbee.entity.promotion.valdisc;

public interface IPromotionDTO {

	String getTypeCode();
	
	String getMechanicCode();

	Boolean isType(String typeCode);
}
