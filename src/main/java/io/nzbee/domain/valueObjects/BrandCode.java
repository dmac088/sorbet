package io.nzbee.domain.valueObjects;


public final class BrandCode {

	private String brandCode;

	public BrandCode(String brandCode) {
		super();
		this.brandCode = brandCode;
	}
	
	public Boolean sameAs(BrandCode other) {
		return this.brandCode.equals(other.brandCode);
	}
	
	@Override
	public String toString() {
        return brandCode;
    }
	
}
