package io.nzbee.domain.valueObjects;


public final class CouponCode {

	private String couponCode;

	public CouponCode(String couponCode) {
		super();
		this.couponCode = couponCode;
	}
	
	public Boolean sameAs(CouponCode other) {
		return this.couponCode.equals(other.couponCode);
	}
	
	@Override
	public String toString() {
        return couponCode;
    }
	
}
