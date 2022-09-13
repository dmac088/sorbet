package io.nzbee.domain.valueObjects;


public final class PromotionTypeCode {

	private String promotionTypeCode;

	public PromotionTypeCode(String promotionTypeCode) {
		super();
		this.promotionTypeCode = promotionTypeCode;
	}
	
	public Boolean sameAs(PromotionTypeCode other) {
		return this.promotionTypeCode.equals(other.promotionTypeCode);
	}
	
	@Override
	public String toString() {
        return promotionTypeCode;
    }
	
}
