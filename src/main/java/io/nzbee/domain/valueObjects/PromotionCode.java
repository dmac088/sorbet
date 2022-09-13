package io.nzbee.domain.valueObjects;


public final class PromotionCode {

	private String promotionCode;

	public PromotionCode(String promotionCode) {
		super();
		this.promotionCode = promotionCode;
	}
	
	public Boolean sameAs(PromotionCode other) {
		return this.promotionCode.equals(other.promotionCode);
	}
	
	@Override
	public String toString() {
        return promotionCode;
    }
	
}
